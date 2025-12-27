package com.zeeyeh.probsolve.questions.impl;

import com.alibaba.fastjson2.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.entity.ImportRow;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.questions.QuestionLibFileHandler;
import com.zeeyeh.probsolve.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Json 题库文件处理器
 */
@Service
public class JsonQuestionLibFileHandler implements QuestionLibFileHandler {

    private static final Logger log = LoggerFactory.getLogger(JsonQuestionLibFileHandler.class);
    private static final int BATCH_SIZE = 200;
    private final QuestionsService questionsService;
    private final QuestionAnswersService questionAnswersService;
    private final QuestionCategoriesService questionCategoriesService;
    private final QuestionCategoryRelationService  questionCategoryRelationService;
    private final QuestionImportTaskService questionImportTaskService;
    private final QuestionImportService questionImportService;

    public JsonQuestionLibFileHandler(QuestionsService questionsService, QuestionAnswersService questionAnswersService, QuestionCategoriesService questionCategoriesService, QuestionCategoryRelationService  questionCategoryRelationService, QuestionImportTaskService questionImportTaskService, QuestionImportService questionImportService) {
        this.questionsService = questionsService;
        this.questionAnswersService = questionAnswersService;
        this.questionCategoriesService = questionCategoriesService;
        this.questionCategoryRelationService = questionCategoryRelationService;
        this.questionImportTaskService = questionImportTaskService;
        this.questionImportService = questionImportService;
    }

    @Override
    public boolean handler(InputStream inputStream, String taskId, Long uid) {
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(inputStream)) {
            Long categoryId;
            String libName = null;
            String signature = null;
            if (parser.nextToken() != JsonToken.START_OBJECT) {
                throw new ServiceException(GlobalError.QUESTION_IMPORT_FAILED);
            }
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getText();
                JsonToken valueToken = parser.nextToken();
                switch (fieldName) {
                    case "lib_name":
                        libName = parser.getValueAsString();
                        break;
                    case "signature":
                        signature = parser.getValueAsString();
                        break;
                    case "questions":
                        categoryId = createCategoryOnce(taskId, libName, signature, uid);

                        if (valueToken != JsonToken.START_ARRAY) {
                            throw new ServiceException(GlobalError.QUESTION_IMPORT_FAILED);
                        }
                        streamReadQuestion(parser, categoryId, uid, taskId);
                        break;
                    default:
                        parser.skipChildren();
                }
            }

            log.info("题库导入完成 taskId: {}", taskId);
            return true;
        } catch (Exception e) {
            log.error("解析题库文件失败: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void streamReadQuestion(JsonParser parser, Long categoryId, Long uid, String taskId) throws IOException {
        List<ImportRow> list = new ArrayList<>(BATCH_SIZE);
        int total = 0;
        int success = 0;
        int error = 0;

        while (parser.nextToken() != JsonToken.END_ARRAY) {
            if (parser.currentToken() != JsonToken.START_OBJECT) {
                parser.skipChildren();
                continue;
            }
            total++;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                TreeNode treeNode = objectMapper.readTree(parser);
                JSONObject questionJson = JSON.parseObject(treeNode.toString());
                // JSONObject questionJson = JSON.parseObject(parser.readValueAsTree().toString());
                QuestionDescription desc = handlerQuestion(questionJson, uid);
                ImportRow importRow = new ImportRow();
                importRow.setQuestions(desc.getQuestions());
                importRow.setQuestionAnswers(desc.getQuestionAnswers());
                list.add(importRow);
                if (list.size() >= BATCH_SIZE) {
                    // questionsService.sa(list, categoryId);
                    questionImportService.saveBatch(list, categoryId);
                    success += list.size();
                    questionImportTaskService.incrementSuccess(taskId, list.size());
                    list.clear();
                }
            } catch (Exception e) {
                error++;
                log.error("题目导入失败， taskId={}", taskId, e);
            }
        }
        if (!list.isEmpty()) {
            try {
                questionImportService.saveBatch(list, categoryId);
                success += list.size();
                questionImportTaskService.incrementSuccess(taskId, list.size());
            } catch (Exception e) {
                error += list.size();
                log.error("题目导入失败， taskId={}", taskId, e);
            }
        }
        if (error > 0) {
            questionImportTaskService.incrementError(taskId, error);
        }
        if (success > 0) {
            questionImportTaskService.incrementSuccess(taskId, success);
        }
        questionImportTaskService.updateTotal(taskId, total);
        questionCategoriesService.updateSize(categoryId, total);
    }

    private Long createCategoryOnce(String taskId, String libName, String signature, Long uid) {
            QueryWrapper queryWrapper = QueryWrapper.create()
                    .eq(QuestionCategories::getName, libName)
                    .eq(QuestionCategories::getUserId, uid);

            if (questionCategoriesService.exists(queryWrapper)) {
                throw new ServiceException(GlobalError.QUESTION_CATEGORY_ALREADY_FOUND);
            }

            QuestionCategories questionCategories = new QuestionCategories();
            questionCategories.setName(libName);
            questionCategories.setSignature(signature);
            questionCategories.setUserId(uid);
            questionCategories.setStatus(1);
            questionCategories.setTaskId(taskId);
            questionCategories.setCreateTime(LocalDateTime.now());
            questionCategories.setUpdateTime(LocalDateTime.now());

            if (!questionCategoriesService.save(questionCategories)) {
                throw new ServiceException(GlobalError.QUESTION_CATEGORY_CREATE_FAILED);
            }
            return questionCategories.getId();
    }

    private void saveQuestionAndAnswer(Questions questions, QuestionAnswers questionAnswers, Long categoryId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Questions::getContent, questions.getContent())
                .eq(Questions::getType, questions.getType());
        if (questionsService.exists(queryWrapper)) {
            return;
        }
        if (!questionsService.save(questions)) {
            log.error("保存题目失败: {}", questions);
            return;
        }
        Questions savedQuestions = questionsService.getOne(queryWrapper);
        Long questionId = savedQuestions.getId();
        questionAnswers.setQuestionId(questionId);
        QueryWrapper qaQueryWrapper = QueryWrapper.create().eq(QuestionAnswers::getQuestionId, questionId);
        if (questionAnswersService.exists(qaQueryWrapper)) {
            return;
        }
        if (!questionAnswersService.save(questionAnswers)) {
            log.error("保存答案失败: {}", questionAnswers);
            return;
        }
        QuestionCategoryRelation relation = new QuestionCategoryRelation();
        relation.setQuestionsId(questionId);
        relation.setCategoryId(categoryId);
        QueryWrapper relationQueryWrapper = QueryWrapper.create()
                .eq(QuestionCategoryRelation::getQuestionsId, questionId)
                .eq(QuestionCategoryRelation::getCategoryId, categoryId);
        if (questionCategoryRelationService.exists(relationQueryWrapper)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_ALREADY_FOUND);
        }
        if (!questionCategoryRelationService.save(relation)) {
            log.error("保存题目分类关联失败: questionId={}, categoryId={}", questionId, categoryId);
            return;
        }
        log.info("保存题目成功: {}", questions);
    }

    private QuestionDescription handlerQuestion(JSONObject jsonObject, Long uid) {
        QuestionDescription questionDescription = new QuestionDescription();
        Questions questions = new Questions();
        String tem = jsonObject.getString("tem");
        Integer difficulty = jsonObject.getInteger("difficulty");
        Integer score = jsonObject.getInteger("score");
        String analysis = jsonObject.getString("analysis");
        Integer type = jsonObject.getInteger("type");
        String tips = jsonObject.getString("tips");
        questions.setCreateTime(LocalDateTime.now());
        questions.setUpdateTime(LocalDateTime.now());
        questions.setContent(tem);
        questions.setScore(score);
        questions.setAnalysis(analysis);
        questions.setStatus(1);
        questions.setUserId(uid);
        questions.setDifficulty(difficulty);
        questions.setType(type);
        QuestionAnswers questionAnswers = new QuestionAnswers();
        questionAnswers.setTips(tips);
        switch (type) {
            case 1:
                // 单选题
                JSONArray singleOptions = jsonObject.getJSONArray("options");
                questionAnswers.setContent(singleOptions.toJSONString());
                Integer singleAnswer = jsonObject.getInteger("answers");
                questionAnswers.setAnswers(singleAnswer.toString());
                break;
            case 2:
                // 多选题
                JSONArray multiOptions = jsonObject.getJSONArray("options");
                questionAnswers.setContent(multiOptions.toJSONString());
                JSONArray multiAnswer = jsonObject.getJSONArray("answers");
                questionAnswers.setAnswers(multiAnswer.toJSONString());
                break;
            case 3:
                // 判断题
                Integer judgeAnswer = jsonObject.getInteger("answers");
                questionAnswers.setAnswers(judgeAnswer.toString());
                break;
            case 4:
                // 填空题
                JSONArray gapFillingAnswers = jsonObject.getJSONArray("answers");
                questionAnswers.setAnswers(gapFillingAnswers.toJSONString());
                break;
            case 5:
                // 简答题
                String shortAnswer = jsonObject.getString("answers");
                questionAnswers.setAnswers(shortAnswer);
                break;
        }
        questionDescription.setQuestions(questions);
        questionDescription.setQuestionAnswers(questionAnswers);
        return questionDescription;
    }

    public static class QuestionDescription {
        private Questions questions;
        private QuestionAnswers questionAnswers;

        public QuestionDescription() {
        }

        public QuestionDescription(Questions questions, QuestionAnswers questionAnswers) {
            this.questions = questions;
            this.questionAnswers = questionAnswers;
        }

        public Questions getQuestions() {
            return questions;
        }

        public QuestionDescription setQuestions(Questions questions) {
            this.questions = questions;
            return this;
        }

        public QuestionAnswers getQuestionAnswers() {
            return questionAnswers;
        }

        public QuestionDescription setQuestionAnswers(QuestionAnswers questionAnswers) {
            this.questionAnswers = questionAnswers;
            return this;
        }
    }

    @Override
    public String getType() {
        return "pb";
    }
}

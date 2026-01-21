package com.zeeyeh.probsolve.question.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.QuestionLibHandler;
import com.zeeyeh.probsolve.question.api.model.entity.*;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON 题库处理器
 *
 * @author Qinloren
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JsonQuestionLibHandler implements QuestionLibHandler {

    private static final int BATCH_SIZE = 200;
    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionCategoryService questionCategoryService;
    private final QuestionCategoryRelationService questionCategoryRelationService;
    private final QuestionImportService questionImportService;


    @Override
    public boolean handler(InputStream inputStream, String taskId, Long uid) {
        JsonFactory factory = new JsonFactory();
        try (JsonParser parser = factory.createParser(inputStream)) {
            Long categoryId;
            String libName = null;
            String signature = null;
            if (parser.nextToken() != JsonToken.START_OBJECT) {
                throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目导入失败");
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
                            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题目导入失败");
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
                importRow.setQuestions(desc.getQuestion());
                importRow.setQuestionAnswers(desc.getQuestionAnswer());
                list.add(importRow);
                if (list.size() >= BATCH_SIZE) {
                    // questionsService.sa(list, categoryId);
                        questionImportService.saveBatch(list, categoryId);
                    success += list.size();
                    questionImportService.incrementSuccess(taskId, list.size());
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
                questionImportService.incrementSuccess(taskId, list.size());
            } catch (Exception e) {
                error += list.size();
                log.error("题目导入失败， taskId={}", taskId, e);
            }
        }
        if (error > 0) {
            questionImportService.incrementError(taskId, error);
        }
        if (success > 0) {
            questionImportService.incrementSuccess(taskId, success);
        }
        questionImportService.updateTotal(taskId, total);
        questionCategoryService.updateSize(categoryId, total);
    }

    private Long createCategoryOnce(String taskId, String libName, String signature, Long uid) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionCategory::getName, libName)
                .eq(QuestionCategory::getUserId, uid);
        if (questionCategoryService.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库已存在");
        }

        QuestionCategory questionCategory = new QuestionCategory();
        questionCategory.setName(libName);
        questionCategory.setSignature(signature);
        questionCategory.setUserId(uid);
        questionCategory.setStatus(QuestionCategoryStatus.SHOW);
        questionCategory.setTaskId(taskId);
        questionCategory.setCreateTime(LocalDateTime.now());
        questionCategory.setUpdateTime(LocalDateTime.now());

        if (!questionCategoryService.save(questionCategory)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库创建失败");
        }
        return questionCategory.getId();
    }

    private void saveQuestionAndAnswer(Question question, QuestionAnswer questionAnswer, Long categoryId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(Question::getContent, question.getContent())
                .eq(Question::getType, question.getType());
        if (questionService.exists(queryWrapper)) {
            return;
        }
        if (!questionService.save(question)) {
            log.error("保存题目失败: {}", question);
            return;
        }
        Question savedQuestions = questionService.getOne(queryWrapper);
        Long questionId = savedQuestions.getId();
        questionAnswer.setQuestionId(questionId);
        QueryWrapper answerQueryWrapper = QueryWrapper.create()
                .eq(QuestionAnswer::getQuestionId, questionId);
        if (questionAnswerService.exists(answerQueryWrapper)) {
            return;
        }
        if (!questionAnswerService.save(questionAnswer)) {
            log.error("保存答案失败: {}", questionAnswer);
            return;
        }
        QuestionCategoryRelation relation = new QuestionCategoryRelation();
        relation.setQuestionId(questionId);
        relation.setCategoryId(categoryId);
        QueryWrapper relationQueryWrapper = QueryWrapper.create()
                .eq(QuestionCategoryRelation::getQuestionId, questionId)
                .eq(QuestionCategoryRelation::getCategoryId, categoryId);
        if (questionCategoryRelationService.exists(relationQueryWrapper)) {
            // throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_ALREADY_FOUND);
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库分类关系已存在");
        }
        if (!questionCategoryRelationService.save(relation)) {
            log.error("保存题目分类关联失败: questionId={}, categoryId={}", questionId, categoryId);
            return;
        }
        log.info("保存题目成功: {}", question);
    }

    private QuestionDescription handlerQuestion(JSONObject jsonObject, Long uid) {
        QuestionDescription questionDescription = new QuestionDescription();
        Question questions = new Question();
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
        questions.setStatus(QuestionStatus.PUBLISHED);
        questions.setUserId(uid);
        questions.setDifficulty(difficulty);
        questions.setType(QuestionType.of(type));
        QuestionAnswer questionAnswers = new QuestionAnswer();
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
        questionDescription.setQuestion(questions);
        questionDescription.setQuestionAnswer(questionAnswers);
        return questionDescription;
    }

    @Override
    public String getType() {
        return "json";
    }
}

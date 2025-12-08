package com.zeeyeh.probsolve.questions.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.questions.QuestionLibFileHandler;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.service.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Json 题库文件处理器
 */
@Service
public class JsonQuestionLibFileHandler implements QuestionLibFileHandler {

    private static final Logger log = LoggerFactory.getLogger(JsonQuestionLibFileHandler.class);
    private final QuestionsService questionsService;
    private final QuestionAnswersService questionAnswersService;
    private final QuestionCategoriesService questionCategoriesService;
    private final QuestionCategoryRelationService  questionCategoryRelationService;

    public JsonQuestionLibFileHandler(QuestionsService questionsService, QuestionAnswersService questionAnswersService, QuestionCategoriesService questionCategoriesService, QuestionCategoryRelationService  questionCategoryRelationService) {
        this.questionsService = questionsService;
        this.questionAnswersService = questionAnswersService;
        this.questionCategoriesService = questionCategoriesService;
        this.questionCategoryRelationService = questionCategoryRelationService;
    }

    @Override
    public boolean handler(byte[] bytes, Long uid) {
        try {
            JSONObject jsonObject = JSON.parseObject(bytes);
            String libName = jsonObject.getString("lib_name");
            JSONArray questions = jsonObject.getJSONArray("question");
            QueryWrapper queryWrapper = QueryWrapper.create().eq(QuestionCategories::getName, libName);
            QuestionCategories questionCategories = new QuestionCategories();
            questionCategories.setName(libName);
            questionCategories.setUserId(uid);
            questionCategories.setStatus(1);
            questionCategories.setCreateTime(LocalDateTime.now());
            questionCategories.setUpdateTime(LocalDateTime.now());
            if (questionCategoriesService.exists(queryWrapper)) {
                log.warn("题库已存在: {}", libName);
                throw new ServiceException(GlobalError.QUESTION_CATEGORY_ALREADY_FOUND);
            }
            if (!questionCategoriesService.save(questionCategories)) {
                log.error("保存题库失败: {}", libName);
                throw new ServiceException(GlobalError.QUESTION_CATEGORY_CREATE_FAILED);
            }
            QuestionCategories saveCategory = questionCategoriesService.getOne(queryWrapper);
            Long categoryId = saveCategory.getId();
            for (Object questionObject : questions) {
                JSONObject question = (JSONObject) questionObject;
                QuestionDescription questionDescription = this.handlerQuestion(question, uid);
                this.saveQuestionAndAnswer(questionDescription.getQuestions(), questionDescription.getQuestionAnswers(), categoryId);
            }
            log.info("保存题库成功: {}", libName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
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
        return "json";
    }
}

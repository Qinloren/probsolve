package com.zeeyeh.probsolve.questions.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.questions.QuestionValidator;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 多选题答案验证器
 */
@Service
public class MultipleChoiceQuestionValidator implements QuestionValidator {

    private final QuestionAnswersService questionAnswersService;

    public MultipleChoiceQuestionValidator(@Lazy QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    @Override
    public boolean validate(Questions questions, Object answer) {
        if (!(answer instanceof List<?>)) {
            return false;
        }
        List<String> answers = (List<String>) answer;
        Long id = questions.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswersService.detail(id);
        String saveAnswers = questionAnswerVo.getAnswers();
        JSONArray saveAnswersArray = JSON.parseArray(saveAnswers);
        if (answers.size() != saveAnswersArray.size()) {
            return false;
        }

        List<String> sortedAnswers = answers.stream().sorted().toList();
        List<String> sortedSaveAnswers = saveAnswersArray.stream().map(Object::toString).sorted().toList();
        return sortedAnswers.equals(sortedSaveAnswers);
    }

    @Override
    public Integer getType() {
        return 2;
    }
}

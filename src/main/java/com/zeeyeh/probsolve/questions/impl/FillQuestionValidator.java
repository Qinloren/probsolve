package com.zeeyeh.probsolve.questions.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.questions.QuestionValidator;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FillQuestionValidator implements QuestionValidator {

    private final QuestionAnswersService questionAnswersService;

    public FillQuestionValidator(@Lazy QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    @Override
    public boolean validate(Questions questions, Object answer) {
        if (!(answer instanceof List<?>)) {
            return false;
        }
        Long id = questions.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswersService.detail(id);
        String saveAnswers = questionAnswerVo.getAnswers();
        JSONArray saveAnswerArray = JSON.parseArray(saveAnswers);
        List<String> answers = (List<String>) answer;
        if (answers.size() != saveAnswerArray.size()) {
            return false;
        }
        List<String> sortedSaveAnswers = new ArrayList<>(Collections.emptyList());
        for (Object o : saveAnswerArray) {
            JSONObject jsonObject = (JSONObject) o;
            Object valueObject = jsonObject.get("value");
            sortedSaveAnswers.add(valueObject.toString());
        }
        return answers.equals(sortedSaveAnswers);
    }

    @Override
    public Integer getType() {
        return 4;
    }
}

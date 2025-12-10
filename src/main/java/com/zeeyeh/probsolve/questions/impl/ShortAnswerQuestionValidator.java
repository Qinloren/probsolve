package com.zeeyeh.probsolve.questions.impl;

import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.questions.QuestionValidator;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ShortAnswerQuestionValidator implements QuestionValidator {

    private final QuestionAnswersService questionAnswersService;

    public ShortAnswerQuestionValidator(@Lazy QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    @Override
    public boolean validate(Questions questions, Object answer) {
        Long id = questions.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswersService.detail(id);
        String answers = questionAnswerVo.getAnswers();
        return answers.equals(answer);
    }

    @Override
    public Integer getType() {
        return 5;
    }
}

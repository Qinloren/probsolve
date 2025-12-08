package com.zeeyeh.probsolve.questions.impl;

import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.questions.QuestionValidator;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 单选题答案验证器
 */
@Service
public class SingleChoiceQuestionValidator implements QuestionValidator {

    private final QuestionAnswersService questionAnswersService;

    public SingleChoiceQuestionValidator(@Lazy QuestionAnswersService questionAnswersService) {
        this.questionAnswersService = questionAnswersService;
    }

    @Override
    public boolean validate(Questions questions, Object answer) {
        Long id = questions.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswersService.detail(id);
        String answers = questionAnswerVo.getAnswers();
        int saveAnswerIndex = Integer.parseInt(answers);
        int answerIndex = (Integer) answer;
        return saveAnswerIndex == answerIndex;
    }

    @Override
    public Integer getType() {
        return 1;
    }
}

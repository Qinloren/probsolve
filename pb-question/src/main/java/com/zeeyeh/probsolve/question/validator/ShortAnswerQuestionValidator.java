package com.zeeyeh.probsolve.question.validator;

import com.zeeyeh.probsolve.question.QuestionValidator;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 简答题验证器
 *
 * @author Qinloren
 */
@Service
public class ShortAnswerQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public ShortAnswerQuestionValidator(@Lazy QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @Override
    public boolean validate(Question question, Object answer) {
        Long id = question.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(id);
        String answers = questionAnswerVo.getAnswers();
        return answers.equals(answer);
    }

    @Override
    public Integer getType() {
        return QuestionType.SHORT_ANSWER.getCode();
    }
}

package com.zeeyeh.probsolve.question.validator;

import com.zeeyeh.probsolve.ErrorBookApi;
import com.zeeyeh.probsolve.ai.provider.ReasonProvider;
import com.zeeyeh.probsolve.question.AbstractQuestionValidator;
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
public class ShortAnswerQuestionValidator extends AbstractQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public ShortAnswerQuestionValidator(
            @Lazy QuestionAnswerService questionAnswerService,
            ErrorBookApi errorBookApi,
            ReasonProvider reasonProvider
    ) {
        super(errorBookApi, reasonProvider);
        this.questionAnswerService = questionAnswerService;
    }

    @Override
    public boolean validate(Long userId, Question question, Object answer) {
        Long id = question.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(id);
        String answers = questionAnswerVo.getAnswers();
        boolean equals = answers.equals(answer);
        if (equals) {
            return true;
        }
        this.processError(userId, "简答题", true, true, question, answer, answers);
        return false;
    }

    @Override
    public Integer getType() {
        return QuestionType.SHORT_ANSWER.getCode();
    }
}

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
 * 判断题验证器
 *
 * @author Qinloren
 */
@Service
public class JudgeQuestionValidator extends AbstractQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public JudgeQuestionValidator(
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
        int saveAnswerIndex = Integer.parseInt(answers);
        int answerIndex = (Integer) answer;
        boolean equals = saveAnswerIndex == answerIndex;
        if (equals) {
            return true;
        }
        this.processError(userId, "判断题", false, false, question, answer, saveAnswerIndex);
        return false;
    }

    @Override
    public Integer getType() {
        return QuestionType.JUDGE.getCode();
    }
}

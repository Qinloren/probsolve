package com.zeeyeh.probsolve.question.validator;

import com.zeeyeh.probsolve.question.QuestionValidator;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 单选题验证器
 *
 * @author Qinloren
 */
@Service
public class SingleQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public SingleQuestionValidator(@Lazy QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @Override
    public boolean validate(Question question, Object answer) {
        Long id = question.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(id);
        String answers = questionAnswerVo.getAnswers();
        int saveAnswerIndex = Integer.parseInt(answers);
        int answerIndex = (Integer) answer;
        return saveAnswerIndex == answerIndex;
    }

    @Override
    public Integer getType() {
        return QuestionType.SINGLE.getCode();
    }
}

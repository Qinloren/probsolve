package com.zeeyeh.probsolve.question.validator;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
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

import java.util.List;

/**
 * 多选题验证器
 *
 * @author Qinloren
 */
@Service
public class MultipleQuestionValidator extends AbstractQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public MultipleQuestionValidator(
            @Lazy QuestionAnswerService questionAnswerService,
            ErrorBookApi errorBookApi,
            ReasonProvider reasonProvider
    ) {
        super(errorBookApi, reasonProvider);
        this.questionAnswerService = questionAnswerService;
    }

    @Override
    public boolean validate(Long userId, Question question, Object answer) {
        if (!(answer instanceof List<?>)) {
            return false;
        }
        List<String> answers = (List<String>) answer;
        Long id = question.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(id);
        String saveAnswers = questionAnswerVo.getAnswers();
        JSONArray saveAnswersArray = JSON.parseArray(saveAnswers);
        if (answers.size() != saveAnswersArray.size()) {
            return false;
        }

        List<String> sortedAnswers = answers.stream().sorted().toList();
        List<String> sortedSaveAnswers = saveAnswersArray.stream().map(Object::toString).sorted().toList();
        boolean equals = sortedAnswers.equals(sortedSaveAnswers);
        if (equals) {
            return true;
        }
        this.processError(userId, "多选题", true, true, question, sortedAnswers, sortedSaveAnswers);
        return false;
    }

    @Override
    public Integer getType() {
        return QuestionType.MULTIPLE.getCode();
    }
}

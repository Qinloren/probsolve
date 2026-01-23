package com.zeeyeh.probsolve.question.validator;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zeeyeh.probsolve.ErrorBookApi;
import com.zeeyeh.probsolve.ai.provider.ReasonProvider;
import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.question.AbstractQuestionValidator;
import com.zeeyeh.probsolve.question.QuestionValidator;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionAnswerVo;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 填空题验证器
 *
 * @author Qinloren
 */
@Service
public class FillQuestionValidator extends AbstractQuestionValidator implements QuestionValidator {

    private final QuestionAnswerService questionAnswerService;

    public FillQuestionValidator(
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
        Long id = question.getId();
        QuestionAnswerVo questionAnswerVo = questionAnswerService.detail(id);
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
        boolean equals = answers.equals(sortedSaveAnswers);
        if (equals) {
            return true;
        }
        this.processError(userId, "填空题", false, false, question, answer, sortedSaveAnswers);
        return false;
    }

    @Override
    public Integer getType() {
        return QuestionType.FILL.getCode();
    }
}

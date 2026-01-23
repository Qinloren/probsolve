package com.zeeyeh.probsolve.question;

import com.alibaba.fastjson2.JSONArray;
import com.zeeyeh.probsolve.ErrorBookApi;
import com.zeeyeh.probsolve.ai.provider.ReasonProvider;
import com.zeeyeh.probsolve.model.dto.ErrorBookCreateDto;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public abstract class AbstractQuestionValidator {
    private final ErrorBookApi errorBookApi;
    private final ReasonProvider reasonProvider;

    protected void processError(Long userId, String type, boolean isChoice, boolean isMultiChoice, Question question, Object answer, Object saveAnswers) {
        String reason = reasonProvider.getReason(
                type,
                isChoice,
                isMultiChoice,
                question.getContent(),
                new JSONArray(),
                answer,
                saveAnswers);
        ErrorBookCreateDto errorBookCreateDto = new ErrorBookCreateDto();
        errorBookCreateDto.setQuestionId(question.getId());
        errorBookCreateDto.setUserId(userId);
        errorBookCreateDto.setReason(reason);
        errorBookApi.create(errorBookCreateDto);
    }
}

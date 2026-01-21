package com.zeeyeh.probsolve.question.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 题目状态
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum QuestionStatus {

    UNREVIEWED(0, "Unreviewed"),
    PUBLISHED(1, "Published"),
    DISCONTINUED(2, "Discontinued");

    @EnumValue
    private final int code;
    private final String description;

    public static QuestionStatus of(int code) {
        for (QuestionStatus questionStatus : QuestionStatus.values()) {
            if (questionStatus.getCode() == code) {
                return questionStatus;
            }
        }
        return UNREVIEWED;
    }
}

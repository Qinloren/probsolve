package com.zeeyeh.probsolve.question.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 题目分类状态
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum QuestionCategoryStatus {

    HIDDEN(0, "隐藏"),
    SHOW(1, "显示")
    ;

    @EnumValue
    private final Integer code;
    private final String text;

    public static QuestionCategoryStatus of(Integer code) {
        for (QuestionCategoryStatus questionCategoryStatus : QuestionCategoryStatus.values()) {
            if (questionCategoryStatus.getCode().equals(code)) {
                return questionCategoryStatus;
            }
        }
        return null;
    }
}

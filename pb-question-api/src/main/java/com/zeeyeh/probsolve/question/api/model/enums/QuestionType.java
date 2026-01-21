package com.zeeyeh.probsolve.question.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 题目类型
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum QuestionType {

    SINGLE(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    FILL(4, "填空"),
    SHORT_ANSWER(5, "简答");

    @EnumValue
    private final Integer code;
    private final String text;

    public static QuestionType of(Integer code) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.getCode().equals(code)) {
                return questionType;
            }
        }
        return null;
    }
}

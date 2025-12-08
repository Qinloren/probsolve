package com.zeeyeh.probsolve.questions;

import com.zeeyeh.probsolve.entity.data.Questions;

public interface QuestionValidator {
    /**
     * 验证答案
     * @param questions 题目
     * @param answer 答案
     * @return 验证结果
     */
    boolean validate(Questions questions, Object answer);

    /**
     * 获取题目类型
     * @return 题目类型
     */
    Integer getType();
}

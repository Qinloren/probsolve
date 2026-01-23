package com.zeeyeh.probsolve.question;

import com.zeeyeh.probsolve.question.api.model.entity.Question;

/**
 * 题目验证器
 *
 * @author Qinloren
 */
public interface QuestionValidator {
    /**
     * 验证答案
     * @param userId 用户 Id
     * @param question 题目
     * @param answer 答案
     * @return 验证结果
     */
    boolean validate(Long userId, Question question, Object answer);

    /**
     * 获取题目类型
     * @return 题目类型
     */
    Integer getType();
}

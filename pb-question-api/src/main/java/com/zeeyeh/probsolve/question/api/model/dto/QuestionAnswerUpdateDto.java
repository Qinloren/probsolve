package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目答案更新参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerUpdateDto {

    /**
     * 答案 Id
     */
    private Long id;

    /**
     * 题目答案选项
     */
    private String content;

    /**
     * 正确答案
     */
    private String answers;

    /**
     * 答题提示
     */
    private String tips;
}

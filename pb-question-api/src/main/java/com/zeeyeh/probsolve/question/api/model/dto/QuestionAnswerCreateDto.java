package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建题目答案请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerCreateDto {

    /**
     * 题目 Id
     */
    private Long questionId;

    /**
     * 答案选项
     */
    private String content;

    /**
     * 非选择题答案
     */
    private String answers;

    /**
     * 答题提示
     */
    private String tips;
}

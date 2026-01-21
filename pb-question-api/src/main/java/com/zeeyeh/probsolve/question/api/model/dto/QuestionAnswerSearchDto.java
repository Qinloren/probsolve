package com.zeeyeh.probsolve.question.api.model.dto;

import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 题目答案搜索请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerSearchDto extends BaseSearchDto {

    /**
     * 答案 Id
     */
    private Long id;

    /**
     * 题目 Id
     */
    private Long questionId;

    /**
     * 题目预设答案
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

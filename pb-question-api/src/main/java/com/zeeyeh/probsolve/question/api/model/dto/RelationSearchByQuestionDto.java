package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 根据题库搜索分类请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationSearchByQuestionDto {
    /**
     * 分类 Id
     */
    private Long categoryId;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 题型
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer size;
}

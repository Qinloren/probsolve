package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关系更新请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationUpdateDto {

    /**
     * 关联 Id
     */
    private Long id;

    /**
     * 题目 Id
     */
    private Long questionsId;

    /**
     * 分类 Id
     */
    private Long categoryId;
}

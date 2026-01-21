package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目分类关系创建请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationCreateDto {

    /**
     * 题目 Id
     */
    private Long questionsId;

    /**
     * 分类 Id
     */
    private Long categoryId;
}

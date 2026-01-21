package com.zeeyeh.probsolve.question.api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;
import com.zeeyeh.probsolve.question.api.serializer.QuestionCategoryStatusDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 题目分类搜索请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategorySearchDto extends BaseSearchDto {

    /**
     * 分类 Id
     */
    private Long id;

    /**
     * 创建者 Id
     */
    private Long userId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 状态(0-隐藏,1-显示)
     */
    @JsonDeserialize(using = QuestionCategoryStatusDeserializer.class)
    private QuestionCategoryStatus status;
}

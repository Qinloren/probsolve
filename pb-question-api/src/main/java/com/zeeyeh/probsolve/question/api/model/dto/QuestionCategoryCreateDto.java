package com.zeeyeh.probsolve.question.api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;
import com.zeeyeh.probsolve.question.api.serializer.QuestionCategoryStatusDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建题库请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategoryCreateDto {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建者 Id
     */
    private Long userId;

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

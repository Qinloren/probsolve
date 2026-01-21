package com.zeeyeh.probsolve.question.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeDeserializer;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeSerializer;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;
import com.zeeyeh.probsolve.question.api.serializer.QuestionCategoryStatusDeserializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionCategoryStatusSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题目分类视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategoryVo {

    /**
     * 分类 Id
     */
    private Long id;

    /**
     * 创建者用户 Id
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
    @JsonSerialize(using = QuestionCategoryStatusSerializer.class)
    private QuestionCategoryStatus status;

    /**
     * 题目数量
     */
    private Integer size;

    /**
     * 题库简介
     */
    private String signature;

    /**
     * 任务 Id
     */
    private String taskId;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    public static QuestionCategoryVo of(QuestionCategory questionCategory) {
        return new QuestionCategoryVo(
                questionCategory.getId(),
                questionCategory.getUserId(),
                questionCategory.getName(),
                questionCategory.getSort(),
                questionCategory.getStatus(),
                questionCategory.getSize(),
                questionCategory.getSignature(),
                questionCategory.getTaskId(),
                questionCategory.getCreateTime(),
                questionCategory.getUpdateTime()
        );
    }
}

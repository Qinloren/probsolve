package com.zeeyeh.probsolve.question.api.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionCategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 题目分类表
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_question_category")
public class QuestionCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类 Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建者用户 Id
     */
    @Column(value = "user_id")
    private long userId;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 状态(0-隐藏,1-显示)
     */
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
    @Column("task_id")
    private String taskId;

    /**
     * 创建时间
     */
    @Column(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(value = "update_time")
    private LocalDateTime updateTime;
}

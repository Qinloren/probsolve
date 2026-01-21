package com.zeeyeh.probsolve.question.imports.task.api.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务表
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_question_import_task")
public class QuestionImportTask implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务 Id
     */
    @Column("task_id")
    private String taskId;

    /**
     * 用户 Id
     */
    @Column("user_id")
    private Long userId;

    /**
     * 总数量
     */
    @Column("total_count")
    private Integer totalCount;

    /**
     * 成功数量
     */
    @Column("success_count")
    private Integer successCount;

    /**
     * 错误数量
     */
    @Column("error_count")
    private Integer errorCount;

    /**
     * 状态
     */
    private QuestionImportTaskStatus status;

    /**
     * 错误信息
     */
    @Column("error_message")
    private String errorMessage;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("update_time")
    private LocalDateTime updateTime;
}

package com.zeeyeh.probsolve.question.imports.task.api.model.vo;

import com.zeeyeh.probsolve.question.imports.task.api.model.entity.QuestionImportTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目导入任务状态视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionImportTaskStatusVo {
    private String taskId;
    private Integer status;

    public static QuestionImportTaskStatusVo of(QuestionImportTask questionImportTask) {
        return new QuestionImportTaskStatusVo(
                questionImportTask.getTaskId(),
                questionImportTask.getStatus().getValue());
    }
}

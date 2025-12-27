package com.zeeyeh.probsolve.vo.basic;

import com.zeeyeh.probsolve.entity.data.QuestionImportTask;

public class QuestionImportTaskStatusVo {
    private String taskId;
    private Integer status;

    public static QuestionImportTaskStatusVo of(QuestionImportTask task) {
        return new QuestionImportTaskStatusVo()
                .setTaskId(task.getTaskId())
                .setStatus(task.getStatus());
    }

    public QuestionImportTaskStatusVo() {
    }

    public QuestionImportTaskStatusVo(String taskId, Integer status) {
        this.taskId = taskId;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public QuestionImportTaskStatusVo setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionImportTaskStatusVo setStatus(Integer status) {
        this.status = status;
        return this;
    }
}

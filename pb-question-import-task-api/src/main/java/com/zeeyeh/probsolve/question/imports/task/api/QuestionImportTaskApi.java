package com.zeeyeh.probsolve.question.imports.task.api;

import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;

/**
 * 题目导入任务 API
 *
 * @author Qinloren
 */
public interface QuestionImportTaskApi {

    /**
     * 创建任务
     * @param taskId 任务 Id
     * @param userId 用户 Id
     */
    void createTask(String taskId, Long userId);

    /**
     * 更新状态
     * @param taskId 任务 Id
     * @param status 状态
     */
    void updateStatus(String taskId, QuestionImportTaskStatus status);

    /**
     * 增加成功数量
     * @param taskId 任务 ID
     * @param count 数量
     */
    void incrementSuccess(String taskId, int count);

    /**
     * 增加失败数量
     * @param taskId 任务 ID
     * @param count 数量
     */
    void incrementError(String taskId, int count);

    /**
     * 完成
     * @param taskId 任务 Id
     */
    void finish(String taskId);

    /**
     * 错误
     * @param taskId 任务 Id
     * @param message 错误信息
     */
    void error(String taskId, String message);

    /**
     * 更新总数量
      * @param taskId 任务 ID
      * @param count 数量
     */
    void updateTotal(String taskId, int count);
}

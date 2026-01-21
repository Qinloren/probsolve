package com.zeeyeh.probsolve.question.imports.task.service;

import com.zeeyeh.probsolve.question.imports.task.api.model.entity.QuestionImportTask;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;
import com.zeeyeh.probsolve.question.imports.task.api.model.vo.QuestionImportTaskStatusVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 题目导入任务表 服务层
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface QuestionImportTaskService {

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
     * 更新总数
     * @param taskId 任务 Id
     * @param total 总数
     */
    void updateTotal(String taskId, int total);

    /**
     * 自增成功
     * @param taskId 任务 Id
     * @param count 成功数
     */
    void incrementSuccess(String taskId, int count);

    /**
     * 自增错误
     * @param taskId 任务 Id
     * @param count 错误数
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
     * 获取任务
     * @param taskId 任务 Id
     * @return 任务
     */
    QuestionImportTask getByTaskId(String taskId);

    /**
     * 批量获取任务状态
     * @param ids 任务 Id
     * @return 任务状态
     */
    List<QuestionImportTaskStatusVo> getStatusBatch(List<String> ids);

    /**
     * 检查任务是否正在执行
     * @param userId 任务 Id
     * @return 是否正在执行
     */
    boolean hasRunningTask(Long userId);
}

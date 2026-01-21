package com.zeeyeh.probsolve.question.imports.task.api.impl;

import com.zeeyeh.probsolve.question.imports.task.api.QuestionImportTaskApi;
import com.zeeyeh.probsolve.question.imports.task.api.model.enums.QuestionImportTaskStatus;
import com.zeeyeh.probsolve.question.imports.task.service.QuestionImportTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 题目导入任务 API
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionImportTaskApiImpl implements QuestionImportTaskApi {

    private final QuestionImportTaskService questionImportTaskService;

    @Override
    public void createTask(String taskId, Long userId) {
        questionImportTaskService.createTask(taskId, userId);
    }

    @Override
    public void updateStatus(String taskId, QuestionImportTaskStatus status) {
        questionImportTaskService.updateStatus(taskId, status);
    }

    @Override
    public void incrementSuccess(String taskId, int count) {
        questionImportTaskService.incrementSuccess(taskId, count);
    }

    @Override
    public void incrementError(String taskId, int count) {
        questionImportTaskService.incrementError(taskId, count);
    }

    @Override
    public void finish(String taskId) {
        questionImportTaskService.finish(taskId);
    }

    @Override
    public void error(String taskId, String message) {
        questionImportTaskService.error(taskId, message);
    }

    @Override
    public void updateTotal(String taskId, int count) {
        questionImportTaskService.updateTotal(taskId, count);
    }
}

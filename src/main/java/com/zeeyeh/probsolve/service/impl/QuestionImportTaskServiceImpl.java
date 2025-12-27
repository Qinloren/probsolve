package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.QuestionImportTask;
import com.zeeyeh.probsolve.mapper.QuestionImportTaskMapper;
import com.zeeyeh.probsolve.service.QuestionImportTaskService;
import com.zeeyeh.probsolve.vo.basic.QuestionImportTaskStatusVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;

@Service
public class QuestionImportTaskServiceImpl extends ServiceImpl<QuestionImportTaskMapper, QuestionImportTask> implements QuestionImportTaskService {


    private final QuestionImportTaskMapper questionImportTaskMapper;

    public QuestionImportTaskServiceImpl(QuestionImportTaskMapper questionImportTaskMapper) {
        this.questionImportTaskMapper = questionImportTaskMapper;
    }

    @Override
    public void createTask(String taskId, Long userId) {
        QuestionImportTask task = new QuestionImportTask();
        task.setTaskId(taskId);
        task.setUserId(userId);
        task.setStatus(QuestionImportTask.PENDING);
        task.setSuccessCount(0);
        task.setErrorCount(0);
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        questionImportTaskMapper.insert(task);
    }

    @Override
    public void updateStatus(String taskId, Integer status) {
        update(taskId, task -> task.setStatus(status));
    }

    @Override
    public void updateTotal(String taskId, int total) {
        update(taskId, task -> task.setTotalCount(total));
    }

    @Override
    public void incrementSuccess(String taskId, int count) {
        update(taskId, task -> task.setSuccessCount(
                Optional.ofNullable(task.getSuccessCount())
                        .orElse(0) + count
        ));
    }

    @Override
    public void incrementError(String taskId, int count) {
        update(taskId, task -> task.setErrorCount(
                Optional.ofNullable(task.getErrorCount())
                        .orElse(0) + count
        ));
    }

    @Override
    public void finish(String taskId) {
        QuestionImportTask task = getByTaskId(taskId);
        if (task == null) return;
        if (task.getErrorCount() != null && task.getErrorCount() > 0) {
            updateStatus(taskId, QuestionImportTask.PARTIAL_SUCCESS);
        } else {
            updateStatus(taskId, QuestionImportTask.SUCCESS);
        }
    }

    @Override
    public void error(String taskId, String message) {
        update(taskId, task -> {
            task.setStatus(QuestionImportTask.ERROR);
            task.setErrorMessage(message);
        });
    }

    @Override
    public QuestionImportTask getByTaskId(String taskId) {
        return this.getOne(this.query().eq(QuestionImportTask::getTaskId, taskId));
    }

    @Override
    public List<QuestionImportTaskStatusVo> getStatusBatch(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<QuestionImportTask> list = this.list(this.query().in(QuestionImportTask::getTaskId, ids));
        return list.stream()
                .map(QuestionImportTaskStatusVo::of)
                .toList();
    }

    @Override
    public boolean hasRunningTask(Long userId) {
        return this.count(this.query()
                .eq(QuestionImportTask::getUserId, userId)
                .in(QuestionImportTask::getStatus,
                        QuestionImportTask.PENDING,
                        QuestionImportTask.RUNNING)) > 0;
    }

    private void update(String taskId, Consumer<QuestionImportTask> updater) {
        QuestionImportTask task = getByTaskId(taskId);
        if (task == null) return;
        updater.accept(task);
        task.setUpdateTime(LocalDateTime.now());
        this.updateById(task);
    }
}

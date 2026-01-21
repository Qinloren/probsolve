package com.zeeyeh.probsolve.question.imports.task.controller;

import com.zeeyeh.probsolve.question.imports.task.api.model.entity.QuestionImportTask;
import com.zeeyeh.probsolve.question.imports.task.api.model.vo.QuestionImportTaskStatusVo;
import com.zeeyeh.probsolve.question.imports.task.service.QuestionImportTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 任务管理接口类
 *
 * @author Qinloren
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("sys/question/task")
public class QuestionImportTaskController {

    private final QuestionImportTaskService questionImportTaskService;

    /**
     * 获取任务状态
     * @param taskId 任务 ID
     * @return 任务状态
     */
    @GetMapping("import/status/{taskId}")
    public Integer getStatus(@PathVariable String taskId) {
        QuestionImportTask task = questionImportTaskService.getByTaskId(taskId);
        return task.getStatus().getValue();
    }

    /**
     * 批量获取任务状态
     * @param ids 任务 ID 列表
     * @return 任务状态列表
     */
    @GetMapping("import/status")
    @ResponseBody
    public List<QuestionImportTaskStatusVo> getStatusBatch(@RequestParam String ids) {
        if (!StringUtils.hasText(ids)) {
            return Collections.emptyList();
        }
        List<String> idList;
        if (!ids.contains(",")) {
            idList = Collections.singletonList(ids);
        } else {
            idList = List.of(ids.split(","));
        }
        return questionImportTaskService.getStatusBatch(idList);
    }
}

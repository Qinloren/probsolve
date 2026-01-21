package com.zeeyeh.probsolve.question.api;

import com.zeeyeh.probsolve.question.api.model.entity.ImportRow;

import java.util.List;

/**
 * 题目导入相关 api
 *
 * @author Qinloren
 */
public interface QuestionImportApi {

    /**
     * 批量保存题目
     * @param importRows 题目列表
     * @param categoryId 分类 ID
     */
    void saveBatch(List<ImportRow> importRows, Long categoryId);

    /**
     * 增加成功数量
     * @param taskId 任务 ID
     * @param count 增加数量
     */
    void incrementSuccess(String taskId, int count);

    /**
     * 增加错误数量
      * @param taskId 任务 ID
      * @param count 增加数量
     */
    void incrementError(String taskId, int count);

    /**
     * 更新总数量
     * @param taskId 任务 ID
     * @param count 增加数量
     */
    void updateTotal(String taskId, int count);
}

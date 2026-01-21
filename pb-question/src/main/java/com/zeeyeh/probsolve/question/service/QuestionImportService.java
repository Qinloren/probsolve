package com.zeeyeh.probsolve.question.service;

import com.zeeyeh.probsolve.question.api.model.entity.ImportRow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 题库导入服务
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface QuestionImportService {

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

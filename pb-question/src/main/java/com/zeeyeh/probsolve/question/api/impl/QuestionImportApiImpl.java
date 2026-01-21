package com.zeeyeh.probsolve.question.api.impl;

import com.zeeyeh.probsolve.question.api.QuestionImportApi;
import com.zeeyeh.probsolve.question.api.model.entity.ImportRow;
import com.zeeyeh.probsolve.question.service.QuestionImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QuestionImportApi 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionImportApiImpl implements QuestionImportApi {

    private final QuestionImportService questionImportService;

    @Override
    public void saveBatch(List<ImportRow> importRows, Long categoryId) {
        questionImportService.saveBatch(importRows, categoryId);
    }

    @Override
    public void incrementSuccess(String taskId, int count) {
        questionImportService.incrementSuccess(taskId, count);
    }

    @Override
    public void incrementError(String taskId, int count) {
        questionImportService.incrementError(taskId, count);
    }

    @Override
    public void updateTotal(String taskId, int count) {
        questionImportService.updateTotal(taskId, count);
    }
}

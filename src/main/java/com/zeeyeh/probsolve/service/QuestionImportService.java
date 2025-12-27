package com.zeeyeh.probsolve.service;

import com.zeeyeh.probsolve.entity.ImportRow;

import java.util.List;

public interface QuestionImportService {

    /**
     * 保存导入的题
     * @param importRows 导入的题
     * @param categoryId 题库id
     */
    void saveBatch(List<ImportRow> importRows, Long categoryId);
}

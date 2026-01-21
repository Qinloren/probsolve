package com.zeeyeh.probsolve.question.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.question.api.QuestionCategoryApi;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;
import com.zeeyeh.probsolve.question.service.QuestionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * QuestionCategoryApi 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionCategoryApiImpl implements QuestionCategoryApi {

    private final QuestionCategoryService questionCategoryService;

    @Override
    public void updateSize(Long categoryId, int size) {
        questionCategoryService.updateSize(categoryId, size);
    }

    @Override
    public boolean exists(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionCategory::getId, id);
        return questionCategoryService.exists(queryWrapper);
    }

    @Override
    public boolean exists(String name, Long userId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionCategory::getName, name)
                .eq(QuestionCategory::getUserId, userId);
        return questionCategoryService.exists(queryWrapper);
    }

    @Override
    public boolean save(QuestionCategory questionCategory) {
        return questionCategoryService.save(questionCategory);
    }

    @Override
    public QuestionCategoryVo detail(Long id) {
        return questionCategoryService.detail(id);
    }
}

package com.zeeyeh.probsolve.question.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.question.api.QuestionCategoryRelationApi;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import com.zeeyeh.probsolve.question.service.QuestionCategoryRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QuestionCategoryRelationApi 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionCategoryRelationApiImpl implements QuestionCategoryRelationApi {

    private final QuestionCategoryRelationService questionCategoryRelationService;

    @Override
    public boolean exists(Long questionId, Long categoryId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionCategoryRelation::getQuestionId, questionId)
                .eq(QuestionCategoryRelation::getCategoryId, categoryId);
        return questionCategoryRelationService.exists(queryWrapper);
    }

    @Override
    public boolean save(QuestionCategoryRelation relation) {
        return questionCategoryRelationService.save(relation);
    }

    @Override
    public void saveBatch(List<QuestionCategoryRelation> relations) {
        questionCategoryRelationService.saveBatch(relations);
    }

    @Override
    public List<QuestionVo> findByCategoryId(Long categoryId) {
        return questionCategoryRelationService.findByCategoryId(categoryId);
    }
}

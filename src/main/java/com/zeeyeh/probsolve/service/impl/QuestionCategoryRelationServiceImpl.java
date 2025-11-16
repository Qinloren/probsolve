package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.question.relation.RelationCreateDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationSearchDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationUpdateDto;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.QuestionCategoryRelationMapper;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.service.QuestionsService;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryRelationVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategoryRelationSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 题目-分类关联表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionCategoryRelationServiceImpl extends ServiceImpl<QuestionCategoryRelationMapper, QuestionCategoryRelation>  implements QuestionCategoryRelationService{
    private final QuestionsService questionsService;
    private final QuestionCategoriesService questionCategoriesService;

    public QuestionCategoryRelationServiceImpl(QuestionsService questionsService, QuestionCategoriesService questionCategoriesService) {
        this.questionsService = questionsService;
        this.questionCategoriesService = questionCategoriesService;
    }

    @Override
    public QuestionCategoryRelationVo create(RelationCreateDto createDto) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(QuestionCategoryRelation::getQuestionsId).eq(createDto.getQuestionsId())
                .and(QuestionCategoryRelation::getCategoryId).eq(createDto.getCategoryId());
        String sql = queryWrapper.toSQL();
        if (this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_ALREADY_FOUND);
        }
        QuestionCategoryRelation relation = new QuestionCategoryRelation();
        if (!questionsService.exists(QueryWrapper.create().eq(Questions::getId, createDto.getQuestionsId()))) {
            throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
        }
        relation.setQuestionsId(createDto.getQuestionsId());
        if (!questionCategoriesService.exists(QueryWrapper.create().eq(QuestionCategories::getId, createDto.getCategoryId()))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
        }
        relation.setCategoryId(createDto.getCategoryId());
        if (!this.save(relation)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_CREATE_FAILED);
        }
        return QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public QuestionCategoryRelationVo update(RelationUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, updateDto.getId()))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_NOT_FOUND);
        }
        UpdateChain<QuestionCategoryRelation> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getQuestionsId())
                .ifPresent(questionsId -> {
                    if (!questionsService.exists(QueryWrapper.create().eq(Questions::getId, updateDto.getQuestionsId()))) {
                        throw new ServiceException(GlobalError.QUESTION_NOT_FOUND);
                    }
                    updatedChain.set(QuestionCategoryRelation::getQuestionsId, questionsId);
                });
        Optional.ofNullable(updateDto.getCategoryId())
                .ifPresent(categoryId -> {
                    if (!questionCategoriesService.exists(QueryWrapper.create().eq(QuestionCategories::getId, updateDto.getCategoryId()))) {
                        throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
                    }
                    updatedChain.set(QuestionCategoryRelation::getCategoryId, categoryId);
                });
        boolean updated = updatedChain.where(QuestionCategoryRelation::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_UPDATE_FAILED);
        }
        QuestionCategoryRelation relation = this.getOne(QueryWrapper.create().eq(QuestionCategoryRelation::getId, updateDto.getId()));
        return QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public QuestionCategoryRelationVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_NOT_FOUND);
        }
        QuestionCategoryRelation relation = this.getOne(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id));
        return QuestionCategoryRelationVo.of(relation);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_NOT_FOUND);
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionCategoryRelation::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_RELATION_DELETE_FAILED);
        }
    }

    @Override
    public QuestionCategoryRelationSearchVo search(RelationSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(QuestionCategoryRelation::getId, id));
        Optional.ofNullable(searchDto.getQuestionsId())
                .ifPresent(questionsId -> queryWrapper.eq(QuestionCategoryRelation::getQuestionsId, questionsId));
        Optional.ofNullable(searchDto.getCategoryId())
                .ifPresent(categoryId -> queryWrapper.eq(QuestionCategoryRelation::getCategoryId, categoryId));
        Page<QuestionCategoryRelation> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionCategoryRelation> relationPage = this.page(page, queryWrapper);
        List<QuestionCategoryRelationVo> list = relationPage.getRecords()
                .stream()
                .map(QuestionCategoryRelationVo::of)
                .toList();
        return new QuestionCategoryRelationSearchVo(
                list,
                relationPage.getTotalPage(),
                relationPage.getPageNumber(),
                relationPage.getPageSize());
    }
}

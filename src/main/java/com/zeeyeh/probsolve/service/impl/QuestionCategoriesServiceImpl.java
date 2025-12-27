package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.QuestionCategoriesMapper;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategorySearchVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 题目分类表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionCategoriesServiceImpl extends ServiceImpl<QuestionCategoriesMapper, QuestionCategories>  implements QuestionCategoriesService{
    @Override
    public QuestionCategoryVo create(QuestionCategoryCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(QuestionCategories::getName, createDto.getName()))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_ALREADY_FOUND);
        }
        QuestionCategories questionCategories = new QuestionCategories();
        questionCategories.setUserId(createDto.getUserId());
        questionCategories.setName(createDto.getName());
        questionCategories.setSort(createDto.getSort());
        questionCategories.setStatus(createDto.getStatus());
        questionCategories.setCreateTime(LocalDateTime.now());
        questionCategories.setUpdateTime(LocalDateTime.now());
        if (!this.save(questionCategories)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_CREATE_FAILED);
        }
        QuestionCategories categories = this.getOne(QueryWrapper.create().eq(QuestionCategories::getName, createDto.getName()));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public QuestionCategoryVo update(QuestionCategoryUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategories::getId, updateDto.getId()))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
        }
        UpdateChain<QuestionCategories> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getName())
                .ifPresent(name -> updatedChain.set(QuestionCategories::getName, name));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> updatedChain.set(QuestionCategories::getUserId, userId));
        Optional.ofNullable(updateDto.getSort())
                .ifPresent(sort -> updatedChain.set(QuestionCategories::getSort, sort));
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updatedChain.set(QuestionCategories::getStatus, status));
        updatedChain.set(QuestionCategories::getUpdateTime, LocalDateTime.now());
        boolean updated = updatedChain.where(QuestionCategories::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_UPDATE_FAILED);
        }
        QuestionCategories categories = this.getOne(QueryWrapper.create().eq(QuestionCategories::getId, updateDto.getId()));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public void updateSize(Long categoryId, int size) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategories::getId, categoryId))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
        }

        boolean updated = this.updateChain()
                .set(QuestionCategories::getSize, size)
                .set(QuestionCategories::getUpdateTime, LocalDateTime.now())
                .where(QuestionCategories::getId)
                .eq(categoryId)
                .update();

        if (!updated) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_UPDATE_FAILED);
        }
    }

    @Override
    public QuestionCategoryVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategories::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
        }
        QuestionCategories categories = this.getOne(QueryWrapper.create().eq(QuestionCategories::getId, id));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategories::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_NOT_FOUND);
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionCategories::getId, id))) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_DELETE_FAILED);
        }
    }

    @Override
    public QuestionCategorySearchVo search(QuestionCategorySearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(QuestionCategories::getId, id));
        Optional.ofNullable(searchDto.getName())
                .ifPresent(name -> queryWrapper.like(QuestionCategories::getName, name));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(QuestionCategories::getUserId, userId));
        Optional.ofNullable(searchDto.getSort())
                .ifPresent(sort -> queryWrapper.eq(QuestionCategories::getSort, sort));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(QuestionCategories::getStatus, status));
        Page<QuestionCategories> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionCategories> categoriesPage = this.page(page, queryWrapper);
        List<QuestionCategoryVo> list = categoriesPage.getRecords()
                .stream()
                .map(QuestionCategoryVo::of)
                .toList();
        return new QuestionCategorySearchVo(
                list,
                categoriesPage.getTotalPage(),
                categoriesPage.getPageNumber(),
                categoriesPage.getPageSize()
        );
    }
}

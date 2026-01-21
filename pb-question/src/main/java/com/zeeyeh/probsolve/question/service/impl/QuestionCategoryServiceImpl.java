package com.zeeyeh.probsolve.question.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategorySearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;
import com.zeeyeh.probsolve.question.mapper.QuestionCategoryMapper;
import com.zeeyeh.probsolve.question.service.QuestionCategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * QuestionCategoryService 实现类
 *
 * @author Qinloren
 */
@Service
public class QuestionCategoryServiceImpl extends ServiceImpl<QuestionCategoryMapper, QuestionCategory> implements QuestionCategoryService {

    @Override
    public void updateSize(Long categoryId, int size) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategory::getId, categoryId))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库不存在");
        }

        boolean updated = this.updateChain()
                .set(QuestionCategory::getSize, size)
                .set(QuestionCategory::getUpdateTime, LocalDateTime.now())
                .where(QuestionCategory::getId)
                .eq(categoryId)
                .update();

        if (!updated) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库更新失败");
        }
    }

    @Override
    public QuestionCategoryVo create(QuestionCategoryCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(QuestionCategory::getName, createDto.getName()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库已存在");
        }
        QuestionCategory questionCategory = new QuestionCategory();
        questionCategory.setUserId(createDto.getUserId());
        questionCategory.setUserId(createDto.getUserId());
        questionCategory.setName(createDto.getName());
        questionCategory.setSort(createDto.getSort());
        questionCategory.setStatus(createDto.getStatus());
        questionCategory.setCreateTime(LocalDateTime.now());
        questionCategory.setUpdateTime(LocalDateTime.now());
        if (!this.save(questionCategory)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库创建失败");
        }
        QuestionCategory categories = this.getOne(QueryWrapper.create().eq(QuestionCategory::getName, createDto.getName()));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public QuestionCategoryVo update(QuestionCategoryUpdateDto updateDto) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategory::getId, updateDto.getId()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库不存在");
        }
        UpdateChain<QuestionCategory> updatedChain = this.updateChain();
        Optional.ofNullable(updateDto.getName())
                .ifPresent(name -> updatedChain.set(QuestionCategory::getName, name));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> updatedChain.set(QuestionCategory::getUserId, userId));
        Optional.ofNullable(updateDto.getSort())
                .ifPresent(sort -> updatedChain.set(QuestionCategory::getSort, sort));
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updatedChain.set(QuestionCategory::getStatus, status));
        updatedChain.set(QuestionCategory::getUpdateTime, LocalDateTime.now());
        boolean updated = updatedChain.where(QuestionCategory::getId)
                .eq(updateDto.getId())
                .update();
        if (!updated) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库更新失败");
        }
        QuestionCategory categories = this.getOne(QueryWrapper.create().eq(QuestionCategory::getId, updateDto.getId()));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public QuestionCategoryVo detail(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategory::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库不存在");
        }
        QuestionCategory categories = this.getOne(QueryWrapper.create().eq(QuestionCategory::getId, id));
        return QuestionCategoryVo.of(categories);
    }

    @Override
    public void delete(Long id) {
        if (!this.exists(QueryWrapper.create().eq(QuestionCategory::getId, id))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题库不存在");
        }
        if (!this.remove(QueryWrapper.create().eq(QuestionCategory::getId, id))) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "题库删除失败");
        }
    }

    @Override
    public QuestionCategorySearchVo search(QuestionCategorySearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(QuestionCategory::getId, id));
        Optional.ofNullable(searchDto.getName())
                .ifPresent(name -> queryWrapper.like(QuestionCategory::getName, name));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(QuestionCategory::getUserId, userId));
        Optional.ofNullable(searchDto.getSort())
                .ifPresent(sort -> queryWrapper.eq(QuestionCategory::getSort, sort));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(QuestionCategory::getStatus, status));
        Page<QuestionCategory> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<QuestionCategory> categoriesPage = this.page(page, queryWrapper);
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

package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.dto.question.category.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategorySearchVo;

/**
 * 题目分类表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
public interface QuestionCategoriesService extends IService<QuestionCategories> {
    /**
     * 创建题目分类
     * @param createDto 创建参数
     * @return 创建结果
     */
    QuestionCategoryVo create(QuestionCategoryCreateDto createDto);

    /**
     * 修改题目分类
     * @param updateDto 修改参数
     * @return 修改结果
     */
    QuestionCategoryVo update(QuestionCategoryUpdateDto updateDto);

    /**
     * 获取题目分类详情
     * @param id id
     * @return 详情
     */
    QuestionCategoryVo detail(Long id);

    /**
     * 删除题目分类
     * @param id id
     */
    void delete(Long id);

    /**
     * 搜索题目分类
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    QuestionCategorySearchVo search(QuestionCategorySearchDto searchDto);
}

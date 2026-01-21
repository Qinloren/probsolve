package com.zeeyeh.probsolve.question.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategorySearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.QuestionCategoryUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategorySearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 题目分类表 服务层
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface QuestionCategoryService extends IService<QuestionCategory> {

    /**
     * 更新题库题目数量
     * @param categoryId 题库 id
     * @param size 题目数量
     */
    void updateSize(Long categoryId, int size);

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

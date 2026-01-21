package com.zeeyeh.probsolve.question.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.question.api.model.dto.RelationCreateDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchByQuestionDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationSearchDto;
import com.zeeyeh.probsolve.question.api.model.dto.RelationUpdateDto;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationSearchVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionCategoryRelationVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionSearchByRelationVo;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 题目-分类关系表 服务层
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface QuestionCategoryRelationService extends IService<QuestionCategoryRelation> {
    /**
     * 创建题目-分类关联关系
     * @param createDto 创建参数
     * @return 创建结果
     */
    QuestionCategoryRelationVo create(RelationCreateDto createDto);

    /**
     * 修改题目-分类关联关系
     * @param updateDto 修改参数
     * @return 修改结果
     */
    QuestionCategoryRelationVo update(RelationUpdateDto updateDto);

    /**
     * 获取题目-分类关联关系详情
     * @param id 关联 Id
     * @return 详情
     */
    QuestionCategoryRelationVo detail(Long id);

    /**
     * 删除题目-分类关联关系
     * @param id 关联 Id
     */
    void delete(Long id);

    /**
     * 搜索题目-分类关联关系
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    QuestionCategoryRelationSearchVo search(RelationSearchDto searchDto);

    /**
     * 通过分类 Id搜索题目
     * @param categoryId 分类 Id
     * @return 搜索结果
     */
    List<QuestionVo> findByCategoryId(Long categoryId);

    /**
     * 通过题目Id搜索题目-分类关联关系
     * @param searchByQuestionDto 搜索参数
     * @return 搜索结果
     */
    QuestionSearchByRelationVo searchByQuestion(RelationSearchByQuestionDto searchByQuestionDto);
}

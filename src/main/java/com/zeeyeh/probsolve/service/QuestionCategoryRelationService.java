package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.question.relation.RelationCreateDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationSearchByQuestionDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationSearchDto;
import com.zeeyeh.probsolve.dto.question.relation.RelationUpdateDto;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryRelationVo;
import com.zeeyeh.probsolve.vo.basic.QuestionByRelationVo;
import com.zeeyeh.probsolve.vo.search.QuestionCategoryRelationSearchVo;
import com.zeeyeh.probsolve.vo.search.QuestionSearchByRelationVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 题目-分类关联表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
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
     * @param id 关联Id
     * @return 详情
     */
    QuestionCategoryRelationVo detail(Long id);

    /**
     * 删除题目-分类关联关系
     * @param id 关联Id
     */
    void delete(Long id);

    /**
     * 搜索题目-分类关联关系
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    QuestionCategoryRelationSearchVo search(RelationSearchDto searchDto);

    /**
     * 通过题目Id搜索题目-分类关联关系
     * @param searchByQuestionDto 搜索参数
     * @return 搜索结果
     */
    QuestionSearchByRelationVo searchByQuestion(RelationSearchByQuestionDto searchByQuestionDto);
}

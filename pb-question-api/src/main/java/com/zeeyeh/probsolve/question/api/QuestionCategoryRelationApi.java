package com.zeeyeh.probsolve.question.api;

import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import com.zeeyeh.probsolve.question.api.model.vo.QuestionVo;

import java.util.List;

/**
 * 题库关系相关 api
 *
 * @author Qinloren
 */
public interface QuestionCategoryRelationApi {

    /**
     * 判断题库和题目是否存在关系
     * @param questionId 题目 id
     * @param categoryId 题库 id
     * @return 是否存在关系
     */
    boolean exists(Long  questionId, Long categoryId);

    /**
     * 保存题库和题目关系
     * @param relation 题库和题目关系
     * @return 是否保存成功
     */
    boolean save(QuestionCategoryRelation relation);

    /**
     * 批量保存题库和题目关系
     *
     * @param relations 题库和题目关系列表
     */
    void saveBatch(List<QuestionCategoryRelation> relations);

    /**
     * 通过分类 Id搜索题目
     * @param categoryId 分类 Id
     * @return 搜索结果
     */
    List<QuestionVo> findByCategoryId(Long categoryId);
}

package com.zeeyeh.probsolve.vo.basic;

import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;

/**
 * 题目-分类关联表 响应实体
 */
public class QuestionCategoryRelationVo {

    /**
     * 关联Id
     */
    private Long id;

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 分类Id
     */
    private Long categoryId;

    public static QuestionCategoryRelationVo of(QuestionCategoryRelation questionCategoryRelation) {
        return new QuestionCategoryRelationVo()
                .setId(questionCategoryRelation.getId())
                .setQuestionsId(questionCategoryRelation.getQuestionsId())
                .setCategoryId(questionCategoryRelation.getCategoryId());
    }

    public QuestionCategoryRelationVo() {
    }

    public QuestionCategoryRelationVo(Long id, Long questionsId, Long categoryId) {
        this.id = id;
        this.questionId = questionsId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public QuestionCategoryRelationVo setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuestionsId() {
        return questionId;
    }

    public QuestionCategoryRelationVo setQuestionsId(Long questionsId) {
        this.questionId = questionsId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public QuestionCategoryRelationVo setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}

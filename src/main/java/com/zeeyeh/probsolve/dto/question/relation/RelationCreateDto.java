package com.zeeyeh.probsolve.dto.question.relation;

/**
 * 题目-分类关联表创建请求参数
 */
public class RelationCreateDto {

    /**
     * 题目Id
     */
    private Long questionsId;

    /**
     * 分类Id
     */
    private Long categoryId;

    public RelationCreateDto() {
    }

    public RelationCreateDto(Long questionsId, Long categoryId) {
        this.questionsId = questionsId;
        this.categoryId = categoryId;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public RelationCreateDto setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public RelationCreateDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}

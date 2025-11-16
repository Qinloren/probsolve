package com.zeeyeh.probsolve.dto.question.relation;

/**
 * 题目-分类关联表更新请求参数
 */
public class RelationUpdateDto {

    /**
     * 关联Id
     */
    private Long id;

    /**
     * 题目Id
     */
    private Long questionsId;

    /**
     * 分类Id
     */
    private Long categoryId;

    public RelationUpdateDto() {
    }

    public RelationUpdateDto(Long id, Long questionsId, Long categoryId) {
        this.id = id;
        this.questionsId = questionsId;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public RelationUpdateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public RelationUpdateDto setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public RelationUpdateDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}

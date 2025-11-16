package com.zeeyeh.probsolve.dto.question.relation;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

/**
 * 题目-分类关联表更新请求参数
 */
public class RelationSearchDto extends BaseSearchDto {

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

    public RelationSearchDto() {
    }

    public RelationSearchDto(Long id, Long questionsId, Long categoryId) {
        this.id = id;
        this.questionsId = questionsId;
        this.categoryId = categoryId;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public Long getId() {
        return id;
    }

    public RelationSearchDto setId(Long id) {
        this.id = id;
        return this;
    }

    public RelationSearchDto setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public RelationSearchDto setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}

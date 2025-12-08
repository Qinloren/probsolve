package com.zeeyeh.probsolve.dto.question.relation;

/**
 * 搜索题库所有题目
 */
public class RelationSearchByQuestionDto {
    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 题型
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer size;

    public RelationSearchByQuestionDto() {
    }

    public RelationSearchByQuestionDto(Long categoryId) {
        this.categoryId = categoryId;
    }

    public RelationSearchByQuestionDto(Long categoryId, Integer difficulty, Integer type, Integer size) {
        this.categoryId = categoryId;
        this.difficulty = difficulty;
        this.type = type;
        this.size = size;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

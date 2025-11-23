package com.zeeyeh.probsolve.dto.question.category;

/**
 * 题目分类创建请求参数
 */
public class QuestionCategoryCreateDto {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建者Id
     */
    private Long userId;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 状态(0-隐藏,1-显示)
     */
    private Integer status;

    public QuestionCategoryCreateDto() {
    }

    public QuestionCategoryCreateDto(String name, Long userId, Integer sort, Integer status) {
        this.name = name;
        this.userId = userId;
        this.sort = sort;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public QuestionCategoryCreateDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionCategoryCreateDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public QuestionCategoryCreateDto setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionCategoryCreateDto setStatus(Integer status) {
        this.status = status;
        return this;
    }
}

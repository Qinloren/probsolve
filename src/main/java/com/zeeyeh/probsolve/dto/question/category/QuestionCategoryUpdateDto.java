package com.zeeyeh.probsolve.dto.question.category;

/**
 * 题目分类更新请求参数
 */
public class QuestionCategoryUpdateDto {

    /**
     * 分类Id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 状态(0-隐藏,1-显示)
     */
    private Integer status;

    public QuestionCategoryUpdateDto() {
    }

    public QuestionCategoryUpdateDto(Long id, String name, Integer sort, Integer status) {
        this.id = id;
        this.name = name;
        this.sort = sort;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public QuestionCategoryUpdateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionCategoryUpdateDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public QuestionCategoryUpdateDto setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionCategoryUpdateDto setStatus(Integer status) {
        this.status = status;
        return this;
    }
}

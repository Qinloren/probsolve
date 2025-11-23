package com.zeeyeh.probsolve.dto.question.category;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

/**
 * 题目分类搜索请求参数
 */
public class QuestionCategorySearchDto extends BaseSearchDto {

    /**
     * 分类Id
     */
    private Long id;

    /**
     * 创建者Id
     */
    private Long userId;

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

    public QuestionCategorySearchDto() {
    }

    public QuestionCategorySearchDto(Long id, Long userId, String name, Integer sort, Integer status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.sort = sort;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public QuestionCategorySearchDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionCategorySearchDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionCategorySearchDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public QuestionCategorySearchDto setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionCategorySearchDto setStatus(Integer status) {
        this.status = status;
        return this;
    }
}

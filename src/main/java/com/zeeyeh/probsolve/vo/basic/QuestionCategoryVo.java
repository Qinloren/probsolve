package com.zeeyeh.probsolve.vo.basic;

import com.zeeyeh.probsolve.entity.data.QuestionCategories;

/**
 * 题目分类响应实体
 */
public class QuestionCategoryVo {

    /**
     * 分类Id
     */
    private Long id;

    /**
     * 创建者用户Id
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

    /**
     * 题目数量
     */
    private Integer size;

    /**
     * 题库简介
     */
    private String signature;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public QuestionCategoryVo() {
    }

    public static QuestionCategoryVo of(QuestionCategories questionCategories) {
        return new QuestionCategoryVo()
                .setId(questionCategories.getId())
                .setName(questionCategories.getName())
                .setUserId(questionCategories.getUserId())
                .setSort(questionCategories.getSort())
                .setStatus(questionCategories.getStatus())
                .setSize(questionCategories.getSize())
                .setSignature(questionCategories.getSignature())
                .setCreateTime(questionCategories.getCreateTimestamp())
                .setUpdateTime(questionCategories.getUpdateTimestamp());
    }

    public QuestionCategoryVo(Long id, Long userId, String name, Integer sort, Integer status, Integer size, String signature, Long createTime, Long updateTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.sort = sort;
        this.status = status;
        this.size = size;
        this.signature = signature;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public QuestionCategoryVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionCategoryVo setName(String name) {
        this.name = name;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionCategoryVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public QuestionCategoryVo setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionCategoryVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public QuestionCategoryVo setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public QuestionCategoryVo setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public QuestionCategoryVo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public QuestionCategoryVo setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}

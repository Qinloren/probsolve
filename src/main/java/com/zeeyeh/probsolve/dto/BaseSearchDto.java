package com.zeeyeh.probsolve.dto;


/**
 * 基础搜索请求实体
 */
public class BaseSearchDto {
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;

    public BaseSearchDto() {
    }

    public BaseSearchDto(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public BaseSearchDto setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseSearchDto setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}

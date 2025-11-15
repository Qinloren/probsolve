package com.zeeyeh.probsolve.vo;

import java.util.List;

public class BaseSearchVo<T> {

    /**
     * 内容
     */
    private List<T> content;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long number;

    /**
     * 总页数
     */
    private Long size;

    public BaseSearchVo() {
    }

    public BaseSearchVo(List<T> content, Long total, Long number, Long size) {
        this.content = content;
        this.total = total;
        this.number = number;
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public BaseSearchVo<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public BaseSearchVo<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public BaseSearchVo<T> setNumber(Long number) {
        this.number = number;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public BaseSearchVo<T> setSize(Long size) {
        this.size = size;
        return this;
    }
}

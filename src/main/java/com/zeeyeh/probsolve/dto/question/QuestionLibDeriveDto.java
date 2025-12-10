package com.zeeyeh.probsolve.dto.question;

/**
 * 题目分类导出请求参数
 */
public class QuestionLibDeriveDto {
    /**
     * 题目分类Id
     */
    private Long id;

    public QuestionLibDeriveDto() {
    }

    public QuestionLibDeriveDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public QuestionLibDeriveDto setId(Long id) {
        this.id = id;
        return this;
    }
}

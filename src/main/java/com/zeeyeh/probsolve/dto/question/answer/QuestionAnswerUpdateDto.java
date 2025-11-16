package com.zeeyeh.probsolve.dto.question.answer;

/**
 * 题目答案更新请求参数
 */
public class QuestionAnswerUpdateDto {

    /**
     * 答案Id
     */
    private Long id;

    /**
     * 题目答案选项
     */
    private String content;

    /**
     * 正确答案
     */
    private String answers;

    /**
     * 答题提示
     */
    private String tips;

    public QuestionAnswerUpdateDto() {
    }

    public QuestionAnswerUpdateDto(Long id, String content, String answers, String tips) {
        this.id = id;
        this.content = content;
        this.answers = answers;
        this.tips = tips;
    }

    public Long getId() {
        return id;
    }

    public QuestionAnswerUpdateDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionAnswerUpdateDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAnswers() {
        return answers;
    }

    public QuestionAnswerUpdateDto setAnswers(String answers) {
        this.answers = answers;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public QuestionAnswerUpdateDto setTips(String tips) {
        this.tips = tips;
        return this;
    }
}

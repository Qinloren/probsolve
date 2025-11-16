package com.zeeyeh.probsolve.dto.question.answer;

public class QuestionAnswerCreateDto {

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 答案选项
     */
    private String content;

    /**
     * 非选择题答案
     */
    private String answers;

    /**
     * 答题提示
     */
    private String tips;

    public QuestionAnswerCreateDto() {
    }

    public QuestionAnswerCreateDto(Long questionId, String content, String answers, String tips) {
        this.questionId = questionId;
        this.content = content;
        this.answers = answers;
        this.tips = tips;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public QuestionAnswerCreateDto setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionAnswerCreateDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAnswers() {
        return answers;
    }

    public QuestionAnswerCreateDto setAnswers(String answers) {
        this.answers = answers;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public QuestionAnswerCreateDto setTips(String tips) {
        this.tips = tips;
        return this;
    }
}

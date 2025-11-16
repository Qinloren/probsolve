package com.zeeyeh.probsolve.dto.question.answer;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

/**
 * 题目答案更新请求参数
 */
public class QuestionAnswerSearchDto extends BaseSearchDto {

    /**
     * 答案Id
     */
    private Long id;

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 题目预设答案
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

    public QuestionAnswerSearchDto() {
    }

    public QuestionAnswerSearchDto(Long id, Long questionId, String content, String answers, String tips) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.answers = answers;
        this.tips = tips;
    }

    public Long getId() {
        return id;
    }

    public QuestionAnswerSearchDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public QuestionAnswerSearchDto setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionAnswerSearchDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAnswers() {
        return answers;
    }

    public QuestionAnswerSearchDto setAnswers(String answers) {
        this.answers = answers;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public QuestionAnswerSearchDto setTips(String tips) {
        this.tips = tips;
        return this;
    }
}

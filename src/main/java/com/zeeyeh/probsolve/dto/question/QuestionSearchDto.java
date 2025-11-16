package com.zeeyeh.probsolve.dto.question;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

/**
 * 题目查询请求参数
 */
public class QuestionSearchDto extends BaseSearchDto {

    /**
     * 题目Id
     */
    private Long id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 来源
     */
    private String source;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建者Id
     */
    private Long userId;

    public QuestionSearchDto() {
    }

    public QuestionSearchDto(Long id, String content, Integer type, Integer difficulty, Integer score, String analysis, String source, Integer status, Long userId) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.score = score;
        this.analysis = analysis;
        this.source = source;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public QuestionSearchDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionSearchDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public QuestionSearchDto setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public QuestionSearchDto setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public QuestionSearchDto setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getAnalysis() {
        return analysis;
    }

    public QuestionSearchDto setAnalysis(String analysis) {
        this.analysis = analysis;
        return this;
    }

    public String getSource() {
        return source;
    }

    public QuestionSearchDto setSource(String source) {
        this.source = source;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionSearchDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionSearchDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

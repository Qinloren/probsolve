package com.zeeyeh.probsolve.dto.question;

/**
 * 题目创建请求参数
 */
public class QuestionCreateDto {

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
     * 状态(0-草稿,1-发布,2-下架)
     */
    private Integer status;

    /**
     * 创建者Id
     */
    private Long userId;

    public QuestionCreateDto() {
    }

    public QuestionCreateDto(String content, Integer type, Integer difficulty, Integer score, String analysis, String source, Integer status, Long userId) {
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.score = score;
        this.analysis = analysis;
        this.source = source;
        this.status = status;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public QuestionCreateDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public QuestionCreateDto setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public QuestionCreateDto setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public QuestionCreateDto setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getAnalysis() {
        return analysis;
    }

    public QuestionCreateDto setAnalysis(String analysis) {
        this.analysis = analysis;
        return this;
    }

    public String getSource() {
        return source;
    }

    public QuestionCreateDto setSource(String source) {
        this.source = source;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionCreateDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionCreateDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

package com.zeeyeh.probsolve.vo.basic;

import com.zeeyeh.probsolve.entity.data.Questions;

/**
 * 题目响应实体
 */
public class QuestionVo {
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
     * 状态(0-草稿,1-发布,2-下架)
     */
    private Integer status;

    /**
     * 创建者Id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public QuestionVo() {
    }

    public QuestionVo(Long id, String content, Integer type, Integer difficulty, Integer score, String analysis, String source, Integer status, Long userId, Long createTime, Long updateTime) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.difficulty = difficulty;
        this.score = score;
        this.analysis = analysis;
        this.source = source;
        this.status = status;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public QuestionVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public QuestionVo setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public QuestionVo setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public QuestionVo setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public QuestionVo setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getAnalysis() {
        return analysis;
    }

    public QuestionVo setAnalysis(String analysis) {
        this.analysis = analysis;
        return this;
    }

    public String getSource() {
        return source;
    }

    public QuestionVo setSource(String source) {
        this.source = source;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QuestionVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public QuestionVo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public QuestionVo setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static QuestionVo of(Questions questions) {
        return new QuestionVo()
                .setId(questions.getId())
                .setContent(questions.getContent())
                .setType(questions.getType())
                .setDifficulty(questions.getDifficulty())
                .setScore(questions.getScore())
                .setAnalysis(questions.getAnalysis())
                .setSource(questions.getSource())
                .setStatus(questions.getStatus())
                .setUserId(questions.getUserId())
                .setCreateTime(questions.getCreateTimestamp())
                .setUpdateTime(questions.getUpdateTimestamp());

    }
}

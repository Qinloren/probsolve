package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * 题目表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_questions")
public class Questions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题目Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题型
     */
    private Integer type;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 题目分值
     */
    private Integer score;

    /**
     * 题目解析
     */
    private String analysis;

    /**
     * 题目来源
     */
    private String source;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建者用户Id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getCreateTimestamp() {
        return createTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTime = createTimestamp;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Long getUpdateTimestamp() {
        return updateTime != null ? updateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTimestamp(Long updateTimestamp) {
        this.updateTime = updateTimestamp != null ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(updateTimestamp), ZoneId.systemDefault()) : null;
    }

}

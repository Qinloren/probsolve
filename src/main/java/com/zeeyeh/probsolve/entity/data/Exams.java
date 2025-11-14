package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;


/**
 * 考试表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_exams")
public class Exams implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 考试Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试说明
     */
    private String description;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 考试时长
     */
    private Integer duration;

    /**
     * 考试开放开始时间(为空随时可考)
     */
    private LocalDateTime startTime;

    /**
     * 考试开放结束时间(为空永久开放)
     */
    private LocalDateTime endTime;

    /**
     * 状态(0-未发布,1-已发布,2-已结束)
     */
    private Integer status;

    /**
     * 创建者Id
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

}

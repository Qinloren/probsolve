package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户考试记录表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
//@Table("pb_user_exam_records")
public class UserExamRecords implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 记录Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户Id
     */
    @Column("user_id")
    private Long userId;

    /**
     * 考试Id
     */
    @Column(value = "exam_id")
    private Long examId;

    /**
     * 开始考试时间
     */
    @Column(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 提交时间
     */
    @Column(value = "submit_time")
    private LocalDateTime submitTime;

    /**
     * 获得总分
     */
    @Column(value = "total_score")
    private Integer totalScore;

    /**
     * 状态(0-进行中,1-已提交,2-已超时)
     */
    private Integer status;

    /**
     * 排名
     */
    private Integer ranking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

}

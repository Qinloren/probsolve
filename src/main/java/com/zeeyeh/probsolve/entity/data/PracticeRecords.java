package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 练习记录表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
//@Table("pb_practice_records")
public class PracticeRecords implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 练习Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户Id
     */
    @Column(value = "user_id")
    private Long userId;

    /**
     * 练习所属分类
     */
    @Column(value = "category_id")
    private Long categoryId;

    /**
     * 练习标题
     */
    private String title;

    /**
     * 开始时间
     */
    @Column(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Column(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 总题数
     */
    @Column(value = "total_questions")
    private Integer totalQuestions;

    /**
     * 做对题数
     */
    @Column(value = "correct_count")
    private Integer correctCount;

    /**
     * 用时(单位: 秒)
     */
    private Integer duration;

    /**
     * 状态(0-进行中,1-已完成)
     */
    private Integer status;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

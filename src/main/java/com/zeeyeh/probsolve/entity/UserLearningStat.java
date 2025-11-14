package com.zeeyeh.probsolve.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Date;

import java.io.Serial;


/**
 * 用户学习统计表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_user_learning_stat")
public class UserLearningStat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 统计Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 统计日期
     */
    private Date statDate;

    /**
     * 当日做题总数
     */
    private Integer totalQuestions;

    /**
     * 当日作对题数
     */
    private Integer correctCount;

    /**
     * 当日参加考试次数
     */
    private Integer examCount;

    /**
     * 当日学习时长
     */
    private Integer studyMinutes;

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

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
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

    public Integer getExamCount() {
        return examCount;
    }

    public void setExamCount(Integer examCount) {
        this.examCount = examCount;
    }

    public Integer getStudyMinutes() {
        return studyMinutes;
    }

    public void setStudyMinutes(Integer studyMinutes) {
        this.studyMinutes = studyMinutes;
    }

}

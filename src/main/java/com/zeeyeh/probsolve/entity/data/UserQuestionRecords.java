package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;


/**
 * 用户答题记录表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_user_question_records")
public class UserQuestionRecords implements Serializable {

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
    private Long userId;

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 所属练习Id
     */
    private Long practiceId;

    /**
     * 所属考试Id
     */
    private Long examId;

    /**
     * 用户答案
     */
    private String userAnswer;

    /**
     * 是否正确
     */
    private Integer isCorrect;

    /**
     * 答题时间
     */
    private LocalDateTime answerTime;

    /**
     * 用时(单位: 秒)
     */
    private Integer spendSeconds;

    /**
     * 用户做题笔记
     */
    private String notes;

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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(Long practiceId) {
        this.practiceId = practiceId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public LocalDateTime getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDateTime answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getSpendSeconds() {
        return spendSeconds;
    }

    public void setSpendSeconds(Integer spendSeconds) {
        this.spendSeconds = spendSeconds;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

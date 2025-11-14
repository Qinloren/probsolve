package com.zeeyeh.probsolve.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;


/**
 * 错题本 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_error_books")
public class ErrorBooks implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错题Id
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
     * 错误原因
     */
    private String reason;

    /**
     * 加入错题本时间
     */
    private LocalDateTime createTime;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}

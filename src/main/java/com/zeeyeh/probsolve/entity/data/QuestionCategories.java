package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * 题目分类表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_question_categories")
public class QuestionCategories implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 创建者用户Id
     */
    private long userId;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 状态(0-隐藏,1-显示)
     */
    private Integer status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public QuestionCategories setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getCreateTimestamp() {
        return createTime != null ? createTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTime = createTimestamp != null ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(createTimestamp), ZoneId.systemDefault()) : null;
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

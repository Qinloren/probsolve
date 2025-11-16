package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

import java.io.Serial;
import java.time.ZoneId;


/**
 * 公告表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_announcements")
public class Announcements implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 公告Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private Integer isTop;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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

    public void setCreateTime(Long createTimestamp) {
        this.createTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(createTimestamp), ZoneId.systemDefault());
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Long getUpdateTimestamp() {
        return updateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTime(Long updateTimestamp) {
        this.updateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTimestamp), ZoneId.systemDefault());
    }
}

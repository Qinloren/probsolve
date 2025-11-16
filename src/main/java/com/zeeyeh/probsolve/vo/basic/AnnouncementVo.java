package com.zeeyeh.probsolve.vo.basic;

import com.zeeyeh.probsolve.entity.data.Announcements;

/**
 * 公告响应实体
 */
public class AnnouncementVo {
    /**
     * 公告Id
     */
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
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;

    public static AnnouncementVo of(Announcements announcements) {
        return new AnnouncementVo()
                .setId(announcements.getId())
                .setTitle(announcements.getTitle())
                .setContent(announcements.getContent())
                .setIsTop(announcements.getIsTop())
                .setStatus(announcements.getStatus())
                .setUserId(announcements.getUserId())
                .setCreateTime(announcements.getCreateTimestamp())
                .setUpdateTime(announcements.getUpdateTimestamp());
    }

    public AnnouncementVo() {
    }

    public AnnouncementVo(Long id, String title, String content, Integer isTop, Integer status, Long userId, Long createTime, Long updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isTop = isTop;
        this.status = status;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public AnnouncementVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AnnouncementVo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AnnouncementVo setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public AnnouncementVo setIsTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AnnouncementVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AnnouncementVo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public AnnouncementVo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public AnnouncementVo setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}

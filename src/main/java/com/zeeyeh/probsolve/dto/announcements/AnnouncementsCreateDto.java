package com.zeeyeh.probsolve.dto.announcements;

/**
 * 公告创建请求参数
 */
public class AnnouncementsCreateDto {
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

    public AnnouncementsCreateDto() {
    }

    public AnnouncementsCreateDto(String title, String content, Integer isTop, Integer status, Long userId) {
        this.title = title;
        this.content = content;
        this.isTop = isTop;
        this.status = status;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public AnnouncementsCreateDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AnnouncementsCreateDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public AnnouncementsCreateDto setIsTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AnnouncementsCreateDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AnnouncementsCreateDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

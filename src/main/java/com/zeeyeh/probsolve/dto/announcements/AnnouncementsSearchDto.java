package com.zeeyeh.probsolve.dto.announcements;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

public class AnnouncementsSearchDto extends BaseSearchDto {
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

    public AnnouncementsSearchDto() {
    }

    public AnnouncementsSearchDto(Integer page, Integer pageSize) {
        super(page, pageSize);
    }

    public AnnouncementsSearchDto(Long id, String title, String content, Integer isTop, Integer status, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isTop = isTop;
        this.status = status;
        this.userId = userId;
    }

    public AnnouncementsSearchDto(Integer page, Integer pageSize, Long id, String title, String content, Integer isTop, Integer status, Long userId) {
        super(page, pageSize);
        this.id = id;
        this.title = title;
        this.content = content;
        this.isTop = isTop;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public AnnouncementsSearchDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AnnouncementsSearchDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AnnouncementsSearchDto setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public AnnouncementsSearchDto setIsTop(Integer isTop) {
        this.isTop = isTop;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AnnouncementsSearchDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AnnouncementsSearchDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

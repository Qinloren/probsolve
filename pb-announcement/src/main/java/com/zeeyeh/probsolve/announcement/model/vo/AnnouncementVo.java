package com.zeeyeh.probsolve.announcement.model.vo;

import com.zeeyeh.probsolve.announcement.model.entity.Announcement;
import com.zeeyeh.probsolve.announcement.model.enums.AnnouncementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 公告视图对象
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementVo {
    /**
     * 公告 Id
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
    private AnnouncementStatus status;
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

    public static AnnouncementVo of(Announcement announcement) {
        return new AnnouncementVo(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getContent(),
                announcement.getIsTop() ? 1 : 0,
                announcement.getStatus(),
                announcement.getUserId(),
                announcement.getCreateTime(),
                announcement.getUpdateTime()
        );
    }
}

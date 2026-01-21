package com.zeeyeh.probsolve.announcement.model.dto;

import com.zeeyeh.probsolve.announcement.model.enums.AnnouncementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告更新请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementUpdateDto {
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
}

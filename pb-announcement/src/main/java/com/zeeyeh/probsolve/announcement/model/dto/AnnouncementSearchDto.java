package com.zeeyeh.probsolve.announcement.model.dto;

import com.zeeyeh.probsolve.announcement.model.enums.AnnouncementStatus;
import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 公告查询请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementSearchDto extends BaseSearchDto {
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

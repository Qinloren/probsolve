package com.zeeyeh.probsolve.announcement.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.zeeyeh.probsolve.announcement.model.enums.AnnouncementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告表
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("pb_announcements")
public class Announcement implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 公告 Id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
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
    @Column("is_top")
    private Boolean isTop;

    /**
     * 公告状态
     */
    private AnnouncementStatus status;

    /**
     * 用户 Id
     */
    @Column("user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("update_time")
    private LocalDateTime updateTime;
}

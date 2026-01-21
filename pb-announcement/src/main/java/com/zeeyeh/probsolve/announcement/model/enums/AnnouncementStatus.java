package com.zeeyeh.probsolve.announcement.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 公告状态
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum AnnouncementStatus {

    DRAFT(0, "draft"),
    PUBLISHED(1, "published"),
    ARCHIVED(2, "archived");

    @EnumValue
    private final Integer value;
    private final String text;
}

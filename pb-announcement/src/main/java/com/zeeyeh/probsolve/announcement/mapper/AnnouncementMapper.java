package com.zeeyeh.probsolve.announcement.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.announcement.model.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告表 映射层。
 *
 * @author Qinloren
 */
@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {
}

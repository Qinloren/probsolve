package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.Announcements;
import com.zeeyeh.probsolve.mapper.AnnouncementsMapper;
import com.zeeyeh.probsolve.service.AnnouncementsService;
import org.springframework.stereotype.Service;

/**
 * 公告表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements>  implements AnnouncementsService{

}

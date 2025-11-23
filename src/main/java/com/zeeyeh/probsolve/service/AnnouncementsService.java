package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsCreateDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsSearchDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsUpdateDto;
import com.zeeyeh.probsolve.entity.data.Announcements;
import com.zeeyeh.probsolve.vo.basic.AnnouncementVo;
import com.zeeyeh.probsolve.vo.search.AnnouncementSearchVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface AnnouncementsService extends IService<Announcements> {

    /**
     * 创建公告接口
     * @param createDto 创建公告参数
     * @return 创建公告结果
     */
    AnnouncementVo create(AnnouncementsCreateDto createDto);

    /**
     * 修改公告接口
     * @param updateDto 修改公告参数
     * @return 修改公告结果
     */
    AnnouncementVo update(AnnouncementsUpdateDto updateDto);

    /**
     * 详情接口
     * @param id 公告id
     * @return 详情
     */
    AnnouncementVo detail(Long id);

    /**
     * 删除接口
     * @param id 公告id
     */
    void delete(Long id);

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    AnnouncementSearchVo search(AnnouncementsSearchDto searchDto);
}

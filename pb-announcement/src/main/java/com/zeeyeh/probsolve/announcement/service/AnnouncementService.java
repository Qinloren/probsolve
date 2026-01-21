package com.zeeyeh.probsolve.announcement.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementCreateDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementSearchDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementUpdateDto;
import com.zeeyeh.probsolve.announcement.model.entity.Announcement;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementSearchVo;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告服务类。
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface AnnouncementService extends IService<Announcement> {

    /**
     * 创建公告接口
     * @param createDto 创建公告参数
     * @return 创建公告结果
     */
    AnnouncementVo create(AnnouncementCreateDto createDto);

    /**
     * 修改公告接口
     * @param updateDto 修改公告参数
     * @return 修改公告结果
     */
    AnnouncementVo update(AnnouncementUpdateDto updateDto);

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
    AnnouncementSearchVo search(AnnouncementSearchDto searchDto);
}

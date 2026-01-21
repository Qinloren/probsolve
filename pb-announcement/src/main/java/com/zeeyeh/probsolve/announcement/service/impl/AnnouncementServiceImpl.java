package com.zeeyeh.probsolve.announcement.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.announcement.mapper.AnnouncementMapper;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementCreateDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementSearchDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementUpdateDto;
import com.zeeyeh.probsolve.announcement.model.entity.Announcement;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementSearchVo;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementVo;
import com.zeeyeh.probsolve.announcement.service.AnnouncementService;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.user.api.UserApi;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 公告服务实现类
 *
 * @author Qinloren
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    private final UserApi userApi;

    public AnnouncementServiceImpl(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public AnnouncementVo create(AnnouncementCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(Announcement::getTitle, createDto.getTitle()))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "公告已存在");
        }
        Announcement announcement = new Announcement();
        announcement.setTitle(createDto.getTitle());
        announcement.setContent(createDto.getContent());
        announcement.setIsTop(createDto.getIsTop());
        announcement.setStatus(createDto.getStatus());
        announcement.setUserId(createDto.getUserId());
        announcement.setCreateTime(LocalDateTime.now());
        announcement.setUpdateTime(LocalDateTime.now());
        if (!this.save(announcement)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "公告创建失败");
        }
        announcement = this.getOne(QueryWrapper.create().eq(Announcement::getTitle, createDto.getTitle()));
        return AnnouncementVo.of(announcement);
    }

    @Override
    public AnnouncementVo update(AnnouncementUpdateDto updateDto) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcement::getId, updateDto.getId());
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "公告不存在");
        }
        UpdateChain<Announcement> updateChain = this.updateChain();
        Optional.ofNullable(updateDto.getTitle())
                .ifPresent(title -> updateChain.set(Announcement::getTitle, title));
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updateChain.set(Announcement::getContent, content));
        Optional.ofNullable(updateDto.getIsTop())
                .ifPresent(isTop -> updateChain.set(Announcement::getIsTop, isTop));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> {
                    if (!userApi.exists(userId)) {
                        throw new ServiceException(ResponseCode.PARAM_ERROR, "用户不存在");
                    }
                    updateChain.set(Announcement::getUserId, userId);
                });
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updateChain.set(Announcement::getStatus, status));
        updateChain.set(Announcement::getUpdateTime, LocalDateTime.now());
        boolean updated = updateChain.where(Announcement::getId).eq(updateDto.getId()).update();
        if (!updated) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "公告更新失败");
        }
        Announcement Announcement = this.getOne(queryWrapper);
        return AnnouncementVo.of(Announcement);
    }

    @Override
    public AnnouncementVo detail(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcement::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "公告不存在");
        }
        Announcement Announcement = this.getOne(queryWrapper);
        return AnnouncementVo.of(Announcement);
    }

    @Override
    public void delete(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcement::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "公告不存在");
        }
        if (!this.remove(queryWrapper)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "公告删除失败");
        }
    }

    @Override
    public AnnouncementSearchVo search(AnnouncementSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        Optional.ofNullable(searchDto.getId())
                .ifPresent(id -> queryWrapper.eq(Announcement::getId, id));
        Optional.ofNullable(searchDto.getTitle())
                .ifPresent(title -> queryWrapper.eq(Announcement::getTitle, title));
        Optional.ofNullable(searchDto.getContent())
                .ifPresent(content -> queryWrapper.eq(Announcement::getContent, content));
        Optional.ofNullable(searchDto.getIsTop())
                .ifPresent(isTop -> queryWrapper.eq(Announcement::getIsTop, isTop));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(Announcement::getStatus, status));
        Optional.ofNullable(searchDto.getUserId())
                .ifPresent(userId -> queryWrapper.eq(Announcement::getUserId, userId));
        Page<Announcement> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<Announcement> announcementsPage = this.page(page, queryWrapper);
        List<AnnouncementVo> list = announcementsPage.getRecords().stream()
                .map(AnnouncementVo::of)
                .toList();
        return new AnnouncementSearchVo(
                list,
                page.getTotalPage(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }
}

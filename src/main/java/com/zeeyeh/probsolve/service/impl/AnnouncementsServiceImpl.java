package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsCreateDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsSearchDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsUpdateDto;
import com.zeeyeh.probsolve.entity.data.Announcements;
import com.zeeyeh.probsolve.entity.data.Users;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.AnnouncementsMapper;
import com.zeeyeh.probsolve.service.AnnouncementsService;
import com.zeeyeh.probsolve.service.UsersService;
import com.zeeyeh.probsolve.vo.basic.AnnouncementVo;
import com.zeeyeh.probsolve.vo.search.AnnouncementSearchVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 公告表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements>  implements AnnouncementsService{


    private final UsersService usersService;

    public AnnouncementsServiceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public AnnouncementVo create(AnnouncementsCreateDto createDto) {
        if (this.exists(QueryWrapper.create().eq(Announcements::getTitle, createDto.getTitle()))) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_ALREADY_FOUND);
        }
        Announcements announcements = new Announcements();
        announcements.setTitle(createDto.getTitle());
        announcements.setContent(createDto.getContent());
        announcements.setIsTop(createDto.getIsTop());
        announcements.setStatus(createDto.getStatus());
        announcements.setUserId(createDto.getUserId());
        announcements.setCreateTime(System.currentTimeMillis());
        announcements.setUpdateTime(System.currentTimeMillis());
        if (!this.save(announcements)) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_CREATE_FAILED);
        }
        announcements = this.getOne(QueryWrapper.create().eq(Announcements::getTitle, createDto.getTitle()));
        return AnnouncementVo.of(announcements);
    }

    @Override
    public AnnouncementVo update(AnnouncementsUpdateDto updateDto) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcements::getId, updateDto.getId());
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_NOT_FOUND);
        }
        UpdateChain<Announcements> updateChain = this.updateChain();
        Optional.ofNullable(updateDto.getTitle())
                .ifPresent(title -> updateChain.set(Announcements::getTitle, title));
        Optional.ofNullable(updateDto.getContent())
                .ifPresent(content -> updateChain.set(Announcements::getContent, content));
        Optional.ofNullable(updateDto.getIsTop())
                .ifPresent(isTop -> updateChain.set(Announcements::getIsTop, isTop));
        Optional.ofNullable(updateDto.getUserId())
                .ifPresent(userId -> {
                    if (!usersService.exists(QueryWrapper.create().eq(Users::getId, userId))) {
                        throw new ServiceException(GlobalError.USER_NOT_FOUND);
                    }
                    updateChain.set(Announcements::getUserId, userId);
                });
        Optional.ofNullable(updateDto.getStatus())
                .ifPresent(status -> updateChain.set(Announcements::getStatus, status));
        updateChain.set(Announcements::getUpdateTime, LocalDateTime.now());
        boolean updated = updateChain.where(Announcements::getId).eq(updateDto.getId()).update();
        if (!updated) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_UPDATE_FAILED);
        }
        Announcements announcements = this.getOne(queryWrapper);
        return AnnouncementVo.of(announcements);
    }

    @Override
    public AnnouncementVo detail(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcements::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_NOT_FOUND);
        }
        Announcements announcements = this.getOne(queryWrapper);
        return AnnouncementVo.of(announcements);
    }

    @Override
    public void delete(Long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Announcements::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_NOT_FOUND);
        }
        if (!this.remove(queryWrapper)) {
            throw new ServiceException(GlobalError.ANNOUNCEMENT_DELETE_FAILED);
        }
    }

    @Override
    public AnnouncementSearchVo search(AnnouncementsSearchDto searchDto) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (searchDto.getId() != null) {
            queryWrapper.eq(Announcements::getId, searchDto.getId());
        }
        if (searchDto.getTitle() != null) {
            queryWrapper.eq(Announcements::getTitle, searchDto.getTitle());
        }
        if (searchDto.getContent() != null) {
            queryWrapper.eq(Announcements::getContent, searchDto.getContent());
        }
        if (searchDto.getIsTop() != null) {
            queryWrapper.eq(Announcements::getIsTop, searchDto.getIsTop());
        }
        if (searchDto.getStatus() != null) {
            queryWrapper.eq(Announcements::getStatus, searchDto.getStatus());
        }
        if (searchDto.getUserId() != null) {
            queryWrapper.eq(Announcements::getUserId, searchDto.getUserId());
        }
        Page<Announcements> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<Announcements> announcementsPage = this.page(page, queryWrapper);
        List<AnnouncementVo> list = announcementsPage.getRecords().stream()
                .map(AnnouncementVo::of)
                .toList();
        return new AnnouncementSearchVo(
                list,
                page.getTotalRow(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }
}

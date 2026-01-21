package com.zeeyeh.probsolve.announcement.controller;

import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementCreateDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementSearchDto;
import com.zeeyeh.probsolve.announcement.model.dto.AnnouncementUpdateDto;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementSearchVo;
import com.zeeyeh.probsolve.announcement.model.vo.AnnouncementVo;
import com.zeeyeh.probsolve.announcement.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

/**
 * 公告接口
 *
 * @author Qinloren
 */
@RestController
@RequestMapping("sys/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementsService;

    public AnnouncementController(AnnouncementService announcementsService) {
        this.announcementsService = announcementsService;
    }

    /**
     * 创建公告接口
     * @param createDto 创建公告参数
     * @return 创建公告结果
     */
    @PostMapping("create")
    @ResponseBody
    public AnnouncementVo create(@RequestBody AnnouncementCreateDto createDto) {
        return announcementsService.create(createDto);
    }

    /**
     * 修改公告接口
     * @param updateDto 修改公告参数
     * @return 修改公告结果
     */
    @PostMapping("update")
    @ResponseBody
    public AnnouncementVo update(@RequestBody AnnouncementUpdateDto updateDto) {
        return announcementsService.update(updateDto);
    }

    /**
     * 获取公告详情接口
     * @param id 公告 id
     * @return 公告详情
     */
    @GetMapping("detail")
    @ResponseBody
    public AnnouncementVo detail(@RequestParam Long id) {
        return announcementsService.detail(id);
    }

    /**
     * 删除公告接口
     * @param id 删除公告参数
     */
    @PostMapping("delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        announcementsService.delete(id);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public AnnouncementSearchVo search(AnnouncementSearchDto searchDto) {
        return announcementsService.search(searchDto);
    }
}

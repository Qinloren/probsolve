package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.announcements.AnnouncementsCreateDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsSearchDto;
import com.zeeyeh.probsolve.dto.announcements.AnnouncementsUpdateDto;
import com.zeeyeh.probsolve.service.AnnouncementsService;
import com.zeeyeh.probsolve.vo.basic.AnnouncementVo;
import com.zeeyeh.probsolve.vo.search.AnnouncementSearchVo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/announcement")
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    public AnnouncementController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    /**
     * 创建公告接口
     * @param createDto 创建公告参数
     * @return 创建公告结果
     */
    @PostMapping("create")
    @ResponseBody
    public AnnouncementVo create(@RequestBody AnnouncementsCreateDto createDto) {
        return announcementsService.create(createDto);
    }

    /**
     * 修改公告接口
     * @param updateDto 修改公告参数
     * @return 修改公告结果
     */
    @PostMapping("update")
    @ResponseBody
    public AnnouncementVo update(@RequestBody AnnouncementsUpdateDto updateDto) {
        return announcementsService.update(updateDto);
    }

    /**
     * 获取公告详情接口
     * @param id 公告id
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
    public AnnouncementSearchVo search(AnnouncementsSearchDto searchDto) {
        return announcementsService.search(searchDto);
    }

}

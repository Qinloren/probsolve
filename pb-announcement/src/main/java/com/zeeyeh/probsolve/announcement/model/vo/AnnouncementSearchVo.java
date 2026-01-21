package com.zeeyeh.probsolve.announcement.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 公告搜索结果
 *
 * @author Qinloren
 */
public class AnnouncementSearchVo extends BaseSearchVo<AnnouncementVo> {
    public AnnouncementSearchVo() {
    }

    public AnnouncementSearchVo(List<AnnouncementVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.AnnouncementVo;

import java.util.List;

public class AnnouncementSearchVo extends BaseSearchVo<AnnouncementVo> {
    public AnnouncementSearchVo() {
    }

    public AnnouncementSearchVo(List<AnnouncementVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

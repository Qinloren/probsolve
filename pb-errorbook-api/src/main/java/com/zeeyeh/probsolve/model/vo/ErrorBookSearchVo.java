package com.zeeyeh.probsolve.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 错题本搜索结果视图对象
 *
 * @author Qinloren
 */
public class ErrorBookSearchVo extends BaseSearchVo<ErrorBookVo> {
    public ErrorBookSearchVo() {
    }

    public ErrorBookSearchVo(List<ErrorBookVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

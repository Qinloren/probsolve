package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.QuestionVo;

import java.util.List;

public class QuestionSearchVo extends BaseSearchVo<QuestionVo> {
    public QuestionSearchVo() {
    }

    public QuestionSearchVo(List<QuestionVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

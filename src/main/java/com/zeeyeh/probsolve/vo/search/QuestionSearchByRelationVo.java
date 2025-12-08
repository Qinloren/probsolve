package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.QuestionByRelationVo;

import java.util.List;

public class QuestionSearchByRelationVo extends BaseSearchVo<QuestionByRelationVo> {
    public QuestionSearchByRelationVo() {
    }

    public QuestionSearchByRelationVo(List<QuestionByRelationVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

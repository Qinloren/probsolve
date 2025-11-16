package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryRelationVo;

import java.util.List;

public class QuestionCategoryRelationSearchVo extends BaseSearchVo<QuestionCategoryRelationVo> {
    public QuestionCategoryRelationSearchVo() {
    }

    public QuestionCategoryRelationSearchVo(List<QuestionCategoryRelationVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

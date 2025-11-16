package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.QuestionCategoryVo;

import java.util.List;

public class QuestionCategorySearchVo extends BaseSearchVo<QuestionCategoryVo> {
    public QuestionCategorySearchVo() {
    }

    public QuestionCategorySearchVo(List<QuestionCategoryVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

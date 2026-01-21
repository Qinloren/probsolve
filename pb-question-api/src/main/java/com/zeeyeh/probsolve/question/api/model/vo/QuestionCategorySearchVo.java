package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 题目分类搜索结果视图对象
 *
 * @author Qinloren
 */
public class QuestionCategorySearchVo extends BaseSearchVo<QuestionCategoryVo> {
    public QuestionCategorySearchVo() {
    }

    public QuestionCategorySearchVo(List<QuestionCategoryVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

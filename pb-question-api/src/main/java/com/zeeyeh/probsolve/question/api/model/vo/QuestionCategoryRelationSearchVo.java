package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 题目分类关系搜索结果
 *
 * @author Qinloren
 */
public class QuestionCategoryRelationSearchVo extends BaseSearchVo<QuestionCategoryRelationVo> {
    public QuestionCategoryRelationSearchVo() {
    }

    public QuestionCategoryRelationSearchVo(List<QuestionCategoryRelationVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

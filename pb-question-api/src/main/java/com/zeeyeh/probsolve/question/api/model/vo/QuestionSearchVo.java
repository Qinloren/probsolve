package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 题目搜索结果视图对象
 *
 * @author Qinloren
 */
public class QuestionSearchVo extends BaseSearchVo<QuestionVo> {
    public QuestionSearchVo() {
    }

    public QuestionSearchVo(List<QuestionVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 题目答案搜索结果
 *
 * @author Qinloren
 */
public class QuestionAnswerSearchVo extends BaseSearchVo<QuestionAnswerVo> {
    public QuestionAnswerSearchVo() {
    }

    public QuestionAnswerSearchVo(List<QuestionAnswerVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

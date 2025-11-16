package com.zeeyeh.probsolve.vo.search;

import com.zeeyeh.probsolve.vo.BaseSearchVo;
import com.zeeyeh.probsolve.vo.basic.QuestionAnswerVo;

import java.util.List;

/**
 * 题目答案响应实体
 */
public class QuestionAnswerSearchVo extends BaseSearchVo<QuestionAnswerVo> {
    public QuestionAnswerSearchVo() {
    }

    public QuestionAnswerSearchVo(List<QuestionAnswerVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

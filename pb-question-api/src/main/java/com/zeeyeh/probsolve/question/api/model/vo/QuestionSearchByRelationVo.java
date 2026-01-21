package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.common.vo.BaseSearchVo;

import java.util.List;

/**
 * 按关系搜索题目视图对象
 *
 * @author Qinloren
 */
public class QuestionSearchByRelationVo extends BaseSearchVo<QuestionByRelationVo> {
    public QuestionSearchByRelationVo() {
    }

    public QuestionSearchByRelationVo(List<QuestionByRelationVo> content, Long total, Long number, Long size) {
        super(content, total, number, size);
    }
}

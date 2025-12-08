package com.zeeyeh.probsolve.vo.basic;

import java.util.List;

public class QuestionByRelationVo extends QuestionVo {
    private List<QuestionOptionsItemVo> options;

    public static QuestionByRelationVo of(QuestionVo questionVo, List<QuestionOptionsItemVo> options) {
        return new QuestionByRelationVo(
                questionVo.getId(),
                questionVo.getContent(),
                questionVo.getType(),
                questionVo.getDifficulty(),
                questionVo.getScore(),
                questionVo.getAnalysis(),
                questionVo.getSource(),
                questionVo.getStatus(),
                questionVo.getUserId(),
                questionVo.getCreateTime(),
                questionVo.getUpdateTime(),
                options
        );
    }

    public QuestionByRelationVo() {
    }

    public QuestionByRelationVo(List<QuestionOptionsItemVo> options) {
        this.options = options;
    }

    public QuestionByRelationVo(Long id, String content, Integer type, Integer difficulty, Integer score, String analysis, String source, Integer status, Long userId, Long createTime, Long updateTime, List<QuestionOptionsItemVo> options) {
        super(id, content, type, difficulty, score, analysis, source, status, userId, createTime, updateTime);
        this.options = options;
    }

    public List<QuestionOptionsItemVo> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionsItemVo> options) {
        this.options = options;
    }
}

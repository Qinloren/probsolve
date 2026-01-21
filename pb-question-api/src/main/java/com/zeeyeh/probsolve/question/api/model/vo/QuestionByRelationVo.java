package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 题目关系试图对象
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    public QuestionByRelationVo(Long id, String content, QuestionType type, Integer difficulty, Integer score, String analysis, String source, QuestionStatus status, Long userId, LocalDateTime createTime, LocalDateTime updateTime, List<QuestionOptionsItemVo> options) {
        super(id, content, type, difficulty, score, analysis, source, status, userId, createTime, updateTime);
        this.options = options;
    }
}

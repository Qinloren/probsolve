package com.zeeyeh.probsolve.question.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeDeserializer;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeSerializer;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.serializer.QuestionStatusDeserializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionStatusSerializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionTypeDeserializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 题目视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo {
    /**
     * 题目 Id
     */
    private Long id;
    /**
     * 题目内容
     */
    private String content;
    /**
     * 题目类型
     */
    @JsonDeserialize(using = QuestionTypeDeserializer.class)
    @JsonSerialize(using = QuestionTypeSerializer.class)
    private QuestionType type;

    /**
     * 难度
     */
    private Integer difficulty;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 解析
     */
    private String analysis;
    /**
     * 来源
     */
    private String source;

    /**
     * 状态(0-草稿,1-发布,2-下架)
     */
    @JsonDeserialize(using = QuestionStatusDeserializer.class)
    @JsonSerialize(using = QuestionStatusSerializer.class)
    private QuestionStatus status;

    /**
     * 创建者 Id
     */
    private Long userId;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    public static QuestionVo of(Question question) {
        return new QuestionVo(
                question.getId(),
                question.getContent(),
                question.getType(),
                question.getDifficulty(),
                question.getScore(),
                question.getAnalysis(),
                question.getSource(),
                question.getStatus(),
                question.getUserId(),
                question.getCreateTime(),
                question.getUpdateTime()
        );
    }
}

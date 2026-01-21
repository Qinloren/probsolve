package com.zeeyeh.probsolve.question.api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.serializer.QuestionStatusDeserializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionTypeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目创建请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型
     */
    @JsonDeserialize(using = QuestionTypeDeserializer.class)
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
    private QuestionStatus status;

    /**
     * 创建者 Id
     */
    private Long userId;
}

package com.zeeyeh.probsolve.question.api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import com.zeeyeh.probsolve.question.api.serializer.QuestionStatusDeserializer;
import com.zeeyeh.probsolve.question.api.serializer.QuestionTypeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 题目搜索请求参数
 *
 * @author Qinloren
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSearchDto extends BaseSearchDto {

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
     * 状态
     */
    @JsonDeserialize(using = QuestionStatusDeserializer.class)
    private QuestionStatus status;

    /**
     * 创建者 Id
     */
    private Long userId;
}

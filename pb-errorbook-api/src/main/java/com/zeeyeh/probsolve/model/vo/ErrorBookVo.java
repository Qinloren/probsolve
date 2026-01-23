package com.zeeyeh.probsolve.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeSerializer;
import com.zeeyeh.probsolve.model.entity.ErrorBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 错题本视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBookVo {

    /**
     * 错题 id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 错误原因
     */
    private String reason;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    public static ErrorBookVo of(ErrorBook errorBook) {
        return new ErrorBookVo(
            errorBook.getId(),
            errorBook.getUserId(),
            errorBook.getQuestionId(),
            errorBook.getReason(),
            errorBook.getCreateTime()
        );
    }
}

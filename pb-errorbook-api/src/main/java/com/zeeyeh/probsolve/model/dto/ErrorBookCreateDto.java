package com.zeeyeh.probsolve.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错题本创建请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBookCreateDto {

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
}

package com.zeeyeh.probsolve.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错题本更新请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBookUpdateDto {

    /**
     * 错题本 Id
     */
    private Long id;

    /**
     * 错误原因
     */
    private String reason;
}

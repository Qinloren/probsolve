package com.zeeyeh.probsolve.model.dto;

import com.zeeyeh.probsolve.common.dto.BaseSearchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 错题本搜索请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBookSearchDto extends BaseSearchDto {

    /**
     * 错题本 Id
     */
    private Long id;

    /**
     * 用户 Id
     */
    private Long userId;

    /**
     * 题目 Id
     */
    private Long questionId;

    /**
     * 错误原因
     */
    private String reason;
}

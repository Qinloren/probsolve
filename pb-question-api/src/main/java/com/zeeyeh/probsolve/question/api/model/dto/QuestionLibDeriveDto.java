package com.zeeyeh.probsolve.question.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题库导出请求参数
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLibDeriveDto {

    /**
     * 题库 id
     */
    private Long id;
}

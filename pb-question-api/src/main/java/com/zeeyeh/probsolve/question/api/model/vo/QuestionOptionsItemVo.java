package com.zeeyeh.probsolve.question.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目选项视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptionsItemVo {
    private int index;
    private String value;
}

package com.zeeyeh.probsolve.question.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 题目描述
 *
 * @author Qinloren
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class QuestionDescription {

    /**
     * 题目对象
     */
    private Question question;

    /**
     * 题目答案对象
     */
    private QuestionAnswer questionAnswer;
}
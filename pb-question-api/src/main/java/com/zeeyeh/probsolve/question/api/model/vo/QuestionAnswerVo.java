package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目答案视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerVo {

    /**
     * 答案 Id
     */
    private Long id;

    /**
     * 题目 Id
     */
    private Long questionId;

    /**
     * 题目预设答案
     */
    private String content;

    /**
     * 正确答案
     */
    private String answers;

    /**
     * 答题提示
     */
    private String tips;

    public static QuestionAnswerVo of(QuestionAnswer questionAnswer) {
        return new QuestionAnswerVo(
                questionAnswer.getId(),
                questionAnswer.getQuestionId(),
                questionAnswer.getContent(),
                questionAnswer.getAnswers(),
                questionAnswer.getTips()
        );
    }
}

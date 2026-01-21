package com.zeeyeh.probsolve.question.api.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 答案表
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_question_answer")
public class QuestionAnswer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 答案 Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 关联题目 Id
     */
    @Column(value = "question_id")
    private Long questionId;

    /**
     * 题目答案选项
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
}

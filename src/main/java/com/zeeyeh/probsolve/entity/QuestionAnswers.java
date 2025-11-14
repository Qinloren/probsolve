package com.zeeyeh.probsolve.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;


/**
 * 标准答案表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_question_answers")
public class QuestionAnswers implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 答案Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 关联题目Id
     */
    private Long questionId;

    /**
     * 标准答案
     */
    private String content;

    /**
     * 答题提示
     */
    private String tips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

}

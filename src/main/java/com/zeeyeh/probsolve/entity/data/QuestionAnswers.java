package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;


/**
 * 标准答案表 实体类。
 * 存储某题的正确答案
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_question_answer")
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

    public String getAnswers() {
        return answers;
    }

    public QuestionAnswers setAnswers(String answers) {
        this.answers = answers;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

}

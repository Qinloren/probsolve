package com.zeeyeh.probsolve.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;


/**
 * 题目选项表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_question_options")
public class QuestionOptions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 选项Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 选项内容
     */
    private String content;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 是否为正确答案(单选题唯一1，多选题可多个1)
     */
    private Integer isCorrect;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

}

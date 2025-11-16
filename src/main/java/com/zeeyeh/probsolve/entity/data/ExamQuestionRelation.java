package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;


/**
 * 考试-题目关联表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_exam_question_relation")
public class ExamQuestionRelation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联Id
     */
    @Id
    private Long id;

    /**
     * 考试Id
     */
    private Long examId;

    /**
     * 题目Id
     */
    private Long questionId;

    /**
     * 题目顺序
     */
    private Integer sort;

    /**
     * 本次考试中的分值
     */
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}

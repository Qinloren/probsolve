package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;


/**
 * 题目-分类关联表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_question_category_relation")
public class QuestionCategoryRelation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 题目Id
     */
    private Long questionsId;

    /**
     * 分类Id
     */
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionsId() {
        return questionsId;
    }

    public void setQuestionsId(Long questionsId) {
        this.questionsId = questionsId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}

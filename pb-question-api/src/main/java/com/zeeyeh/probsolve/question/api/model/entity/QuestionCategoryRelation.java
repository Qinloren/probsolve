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
 * 题库分类关联表
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_question_category_relation")
public class QuestionCategoryRelation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关联 Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 题目 Id
     */
    @Column(value = "question_id")
    private Long questionId;

    /**
     * 分类 Id
     */
    @Column(value = "category_id")
    private Long categoryId;
}

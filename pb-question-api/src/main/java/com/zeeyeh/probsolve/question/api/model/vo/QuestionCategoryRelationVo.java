package com.zeeyeh.probsolve.question.api.model.vo;

import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目分类关联视图对象
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCategoryRelationVo {

    /**
     * 关联 Id
     */
    private Long id;

    /**
     * 题目 Id
     */
    private Long questionId;

    /**
     * 分类 Id
     */
    private Long categoryId;

    public static QuestionCategoryRelationVo of(QuestionCategoryRelation relation) {
        return new QuestionCategoryRelationVo(
                relation.getId(),
                relation.getQuestionId(),
                relation.getCategoryId());
    }
}

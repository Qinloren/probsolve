package com.zeeyeh.probsolve.question.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题库-题目关系表 映射层
 *
 * @author Qinloren
 */
@Mapper
public interface QuestionCategoryRelationMapper extends BaseMapper<QuestionCategoryRelation> {
}

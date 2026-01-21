package com.zeeyeh.probsolve.question.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题库表 映射层
 *
 * @author Qinloren
 */
@Mapper
public interface QuestionCategoryMapper extends BaseMapper<QuestionCategory> {
}

package com.zeeyeh.probsolve.question.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目表 映射层
 *
 * @author Qinloren
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}

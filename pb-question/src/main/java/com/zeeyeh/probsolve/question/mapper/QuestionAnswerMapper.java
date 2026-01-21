package com.zeeyeh.probsolve.question.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目答案表 映射层
 *
 * @author Qinloren
 */
@Mapper
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
}

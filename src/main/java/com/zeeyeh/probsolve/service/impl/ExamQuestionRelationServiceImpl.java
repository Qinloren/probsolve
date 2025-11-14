package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.ExamQuestionRelation;
import com.zeeyeh.probsolve.mapper.ExamQuestionRelationMapper;
import com.zeeyeh.probsolve.service.ExamQuestionRelationService;
import org.springframework.stereotype.Service;

/**
 * 考试-题目关联表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class ExamQuestionRelationServiceImpl extends ServiceImpl<ExamQuestionRelationMapper, ExamQuestionRelation>  implements ExamQuestionRelationService{

}

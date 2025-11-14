package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.QuestionTagRelation;
import com.zeeyeh.probsolve.mapper.QuestionTagRelationMapper;
import com.zeeyeh.probsolve.service.QuestionTagRelationService;
import org.springframework.stereotype.Service;

/**
 * 题目-标签关联表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionTagRelationServiceImpl extends ServiceImpl<QuestionTagRelationMapper, QuestionTagRelation>  implements QuestionTagRelationService{

}

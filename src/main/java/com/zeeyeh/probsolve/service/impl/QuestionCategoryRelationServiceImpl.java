package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.mapper.QuestionCategoryRelationMapper;
import com.zeeyeh.probsolve.service.QuestionCategoryRelationService;
import org.springframework.stereotype.Service;

/**
 * 题目-分类关联表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionCategoryRelationServiceImpl extends ServiceImpl<QuestionCategoryRelationMapper, QuestionCategoryRelation>  implements QuestionCategoryRelationService{

}

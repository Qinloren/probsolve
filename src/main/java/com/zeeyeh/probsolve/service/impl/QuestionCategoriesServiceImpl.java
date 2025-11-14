package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.QuestionCategories;
import com.zeeyeh.probsolve.mapper.QuestionCategoriesMapper;
import com.zeeyeh.probsolve.service.QuestionCategoriesService;
import org.springframework.stereotype.Service;

/**
 * 题目分类表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionCategoriesServiceImpl extends ServiceImpl<QuestionCategoriesMapper, QuestionCategories>  implements QuestionCategoriesService{

}

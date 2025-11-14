package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.QuestionTags;
import com.zeeyeh.probsolve.mapper.QuestionTagsMapper;
import com.zeeyeh.probsolve.service.QuestionTagsService;
import org.springframework.stereotype.Service;

/**
 * 题目标签表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionTagsServiceImpl extends ServiceImpl<QuestionTagsMapper, QuestionTags>  implements QuestionTagsService{

}

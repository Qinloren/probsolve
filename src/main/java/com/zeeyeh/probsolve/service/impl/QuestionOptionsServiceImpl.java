package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.QuestionOptions;
import com.zeeyeh.probsolve.mapper.QuestionOptionsMapper;
import com.zeeyeh.probsolve.service.QuestionOptionsService;
import org.springframework.stereotype.Service;

/**
 * 题目选项表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionOptionsServiceImpl extends ServiceImpl<QuestionOptionsMapper, QuestionOptions>  implements QuestionOptionsService{

}

package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.mapper.QuestionsMapper;
import com.zeeyeh.probsolve.service.QuestionsService;
import org.springframework.stereotype.Service;

/**
 * 题目表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>  implements QuestionsService{

}

package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.mapper.QuestionAnswersMapper;
import com.zeeyeh.probsolve.service.QuestionAnswersService;
import org.springframework.stereotype.Service;

/**
 * 标准答案表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class QuestionAnswersServiceImpl extends ServiceImpl<QuestionAnswersMapper, QuestionAnswers>  implements QuestionAnswersService{

}

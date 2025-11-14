package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.UserQuestionRecords;
import com.zeeyeh.probsolve.mapper.UserQuestionRecordsMapper;
import com.zeeyeh.probsolve.service.UserQuestionRecordsService;
import org.springframework.stereotype.Service;

/**
 * 用户答题记录表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UserQuestionRecordsServiceImpl extends ServiceImpl<UserQuestionRecordsMapper, UserQuestionRecords>  implements UserQuestionRecordsService{

}

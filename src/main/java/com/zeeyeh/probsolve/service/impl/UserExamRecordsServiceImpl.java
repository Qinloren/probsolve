package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.UserExamRecords;
import com.zeeyeh.probsolve.mapper.UserExamRecordsMapper;
import com.zeeyeh.probsolve.service.UserExamRecordsService;
import org.springframework.stereotype.Service;

/**
 * 用户考试记录表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UserExamRecordsServiceImpl extends ServiceImpl<UserExamRecordsMapper, UserExamRecords>  implements UserExamRecordsService{

}

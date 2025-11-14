package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.UserLearningStat;
import com.zeeyeh.probsolve.mapper.UserLearningStatMapper;
import com.zeeyeh.probsolve.service.UserLearningStatService;
import org.springframework.stereotype.Service;

/**
 * 用户学习统计表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UserLearningStatServiceImpl extends ServiceImpl<UserLearningStatMapper, UserLearningStat>  implements UserLearningStatService{

}

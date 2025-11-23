package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.UserLearningStat;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户学习统计表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface UserLearningStatService extends IService<UserLearningStat> {

}

package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.UserQuestionRecords;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户答题记录表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface UserQuestionRecordsService extends IService<UserQuestionRecords> {

}

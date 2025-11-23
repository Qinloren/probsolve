package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.UserProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户资料表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface UserProfilesService extends IService<UserProfiles> {

}

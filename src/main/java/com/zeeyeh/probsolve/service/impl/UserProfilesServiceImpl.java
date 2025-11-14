package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.UserProfiles;
import com.zeeyeh.probsolve.mapper.UserProfilesMapper;
import com.zeeyeh.probsolve.service.UserProfilesService;
import org.springframework.stereotype.Service;

/**
 * 用户资料表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UserProfilesServiceImpl extends ServiceImpl<UserProfilesMapper, UserProfiles>  implements UserProfilesService{

}

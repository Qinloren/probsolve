package com.zeeyeh.probsolve.user.api.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.user.api.UserApi;
import com.zeeyeh.probsolve.user.api.model.entity.User;
import com.zeeyeh.probsolve.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * UserApi 实现类
 *
 * @author Qinloren
 */
@Service
public class UserApiImpl implements UserApi {
    private final UserService userService;

    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean exists(Long userId) {
        return userService.exists(QueryWrapper.create().eq(User::getId, userId));
    }
}

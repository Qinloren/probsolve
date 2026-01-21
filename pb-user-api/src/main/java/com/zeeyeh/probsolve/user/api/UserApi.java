package com.zeeyeh.probsolve.user.api;

/**
 * 用户 api
 *
 * @author Qinloren
 */
public interface UserApi {

    /**
     * 判断用户是否存在
     * @param userId 用户 id
     * @return 是否存在
     */
    boolean exists(Long userId);
}

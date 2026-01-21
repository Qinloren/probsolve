package com.zeeyeh.probsolve.user.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录请求参数
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 持久化登录
     */
    private boolean persistent;
}

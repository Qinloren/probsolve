package com.zeeyeh.probsolve.user.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册请求参数
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;
}

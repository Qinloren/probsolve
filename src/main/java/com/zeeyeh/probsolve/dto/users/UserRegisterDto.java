package com.zeeyeh.probsolve.dto.users;

/**
 * 用户注册请求实体
 */
/**
 * 用户注册请求实体
 */
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

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String email, String avatar, String password) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserRegisterDto setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
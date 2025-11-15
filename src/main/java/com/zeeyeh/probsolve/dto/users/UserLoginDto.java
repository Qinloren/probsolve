package com.zeeyeh.probsolve.dto.users;

/**
 * 用户登录请求实体
 */
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

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String email, String password, boolean persistent) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.persistent = persistent;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserLoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public UserLoginDto setPersistent(boolean persistent) {
        this.persistent = persistent;
        return this;
    }
}
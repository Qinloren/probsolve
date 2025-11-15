package com.zeeyeh.probsolve.dto.users;


/**
 * 用户创建请求实体
 */

/**
 * 用户创建请求实体
 */
public class UserCreateDto {
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
     * 角色(0-普通用户,1-管理员)
     */
    private Integer role;

    /**
     * 状态(0-暂停,1-正常)
     */
    private Integer status;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 总积分
     */
    private Integer totalScore;

    /**
     * 用户等级
     */
    private Integer level;

    public UserCreateDto() {
    }

    public UserCreateDto(String username, String email, String password, Integer role, Integer status, String avatar, Integer totalScore, Integer level) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
        this.totalScore = totalScore;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public UserCreateDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserCreateDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCreateDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public UserCreateDto setRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserCreateDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserCreateDto setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public UserCreateDto setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public UserCreateDto setLevel(Integer level) {
        this.level = level;
        return this;
    }
}

package com.zeeyeh.probsolve.dto.users;

import com.zeeyeh.probsolve.dto.BaseSearchDto;

/**
 * 用户搜索请求实体
 */
public class UserSearchDto extends BaseSearchDto {
    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 角色(0-普通用户,1-管理员)
     */
    private String role;

    /**
     * 状态(0-暂停,1-正常)
     */
    private String status;

    /**
     * 总积分
     */
    private String totalScore;

    /**
     * 用户等级
     */
    private String level;

    public UserSearchDto() {
    }

    public UserSearchDto(String username, String email, String role, String status, String totalScore, String level) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.totalScore = totalScore;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public UserSearchDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSearchDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserSearchDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UserSearchDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public UserSearchDto setTotalScore(String totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public UserSearchDto setLevel(String level) {
        this.level = level;
        return this;
    }
}

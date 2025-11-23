package com.zeeyeh.probsolve.vo.basic;


import com.zeeyeh.probsolve.entity.data.Users;

import java.math.BigDecimal;


/**
 * 用户响应实体
 */
public class UserVo {

    /**
 * 用户Id
 */
    private Long id;

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

    /**
 * 创建时间
 */
    private Long createTime;

    /**
 * 更新时间
 */
    private Long updateTime;

    /**
 * 最后登录时间
 */
    private Long lastLoginTime;

    /**
     * 总答题次数
     */
    private Integer totalAttempts;

    /**
     * 正确率
     */
    private BigDecimal correctRate;

    private Integer totalExams;

    public static UserVo of(Users user) {
        return new UserVo()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setStatus(user.getStatus())
                .setAvatar(user.getAvatar())
                .setTotalScore(user.getTotalScore())
                .setLevel(user.getLevel())
                .setCreateTime(user.getCreateTimestamp())
                .setUpdateTime(user.getUpdateTimestamp())
                .setLastLoginTime(user.getLastLoginTimestamp())
                .setTotalAttempts(user.getTotalAttempts())
                .setCorrectRate(user.getCorrectRate())
                .setTotalExams(user.getTotalExams());
    }

    public UserVo() {
    }

    public UserVo(Long id, String username, String email, Integer role, Integer status, String avatar, Integer totalScore, Integer level, Long createTime, Long updateTime, Long lastLoginTime, Integer totalAttempts, BigDecimal correctRate, Integer totalExams) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatar = avatar;
        this.totalScore = totalScore;
        this.level = level;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastLoginTime = lastLoginTime;
        this.totalAttempts = totalAttempts;
        this.correctRate = correctRate;
        this.totalExams = totalExams;
    }

    public Long getId() {
        return id;
    }

    public UserVo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserVo setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public UserVo setRole(Integer role) {
        this.role = role;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserVo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserVo setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public UserVo setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public UserVo setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public UserVo setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public UserVo setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public UserVo setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Integer getTotalAttempts() {
        return totalAttempts;
    }

    public UserVo setTotalAttempts(Integer totalAttempts) {
        this.totalAttempts = totalAttempts;
        return this;
    }

    public BigDecimal getCorrectRate() {
        return correctRate;
    }

    public UserVo setCorrectRate(BigDecimal correctRate) {
        this.correctRate = correctRate;
        return this;
    }

    public Integer getTotalExams() {
        return totalExams;
    }

    public UserVo setTotalExams(Integer totalExams) {
        this.totalExams = totalExams;
        return this;
    }
}

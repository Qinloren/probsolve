package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.zeeyeh.probsolve.entity.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

import java.io.Serial;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;


/**
 * 用户表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Table("pb_users")
public class Users implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @Id(keyType = KeyType.Auto)
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

    /**
     * 注册时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role == 1 ? "ADMIN" : "USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == UserStatus.PAUSE.ordinal();
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == UserStatus.PAUSE.ordinal();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.NORMAL.ordinal();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Long getCreateTimestamp() {
        return createTime != null ? createTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(Long createTimestamp) {
        this.createTime = createTimestamp != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(createTimestamp), ZoneId.systemDefault()) : null;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Long getUpdateTimestamp() {
        return updateTime != null ? updateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTime(Long updateTimestamp) {
        this.updateTime = updateTimestamp != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(updateTimestamp), ZoneId.systemDefault()) : null;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public Long getLastLoginTimestamp() {
        return lastLoginTime != null ? lastLoginTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTimestamp) {
        this.lastLoginTime = lastLoginTimestamp != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(lastLoginTimestamp), ZoneId.systemDefault()) : null;
    }
}

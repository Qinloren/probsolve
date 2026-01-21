package com.zeeyeh.probsolve.user.api.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 用户表
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("pb_users")
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private UserRole role;

    /**
     * 状态
     */
    private UserStatus status;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 总积分
     */
    @Column("total_score")
    private Integer totalScore;

    /**
     * 用户等级
     */
    private Integer level;

    /**
     * 注册时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("update_time")
    private LocalDateTime updateTime;

    /**
     * 登录时间
     */
    @Column("login_time")
    private LocalDateTime loginTime;

    /**
     * 总答题次数
     */
    @Column("total_attempts")
    private Integer totalAttempts;

    /**
     * 正确率
     */
    @Column("correct_rate")
    private BigDecimal correctRate;

    /**
     * 总考试次数
     */
    @Column("total_exams")
    private Integer totalExams;

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getText()));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public @NonNull String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserStatus.PAUSE == status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatus.PAUSE == status;
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.NORMAL == status;
    }
}

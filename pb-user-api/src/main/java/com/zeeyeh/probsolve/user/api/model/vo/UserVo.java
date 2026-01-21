package com.zeeyeh.probsolve.user.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zeeyeh.probsolve.common.serializer.LocalDateTimeSerializer;
import com.zeeyeh.probsolve.user.api.model.entity.User;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;
import com.zeeyeh.probsolve.user.api.serializer.UserRoleSerializer;
import com.zeeyeh.probsolve.user.api.serializer.UserStatusSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户视图对象
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    /**
     * 用户 Id
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
     * 角色
     */
    @JsonSerialize(using = UserRoleSerializer.class)
    private UserRole role;
    /**
     * 状态
     */
    @JsonSerialize(using = UserStatusSerializer.class)
    private UserStatus status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 总分
     */
    private Integer totalScore;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;
    /**
     * 登录时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime loginTime;

    /**
     * 总共答题的次数
     */
    private Integer totalAttempts;
    /**
     * 正确率
     */
    private BigDecimal correctRate;
    /**
     * 总共参加的考试次数
     */
    private Integer totalExams;

    /**
     * 将 User 对象转换为 UserVo 对象
     * @param user 用户对象
     * @return 用户视图对象
     */
    public static UserVo of(User user) {
        return new UserVo(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getAvatar(),
                user.getTotalScore(),
                user.getLevel(),
                user.getCreateTime(),
                user.getUpdateTime(),
                user.getLoginTime(),
                user.getTotalAttempts(),
                user.getCorrectRate(),
                user.getTotalExams()
        );
    }
}

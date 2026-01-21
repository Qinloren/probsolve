package com.zeeyeh.probsolve.user.api.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;
import com.zeeyeh.probsolve.user.api.serializer.UserRoleDeserializer;
import com.zeeyeh.probsolve.user.api.serializer.UserStatusDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户创建请求参数
 *
 * @author Qinloren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonDeserialize(using = UserRoleDeserializer.class)
    private UserRole role;

    /**
     * 状态(0-暂停,1-正常)
     */
    @JsonDeserialize(using = UserStatusDeserializer.class)
    private UserStatus status;

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
}

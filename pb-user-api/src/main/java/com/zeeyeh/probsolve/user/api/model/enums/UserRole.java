package com.zeeyeh.probsolve.user.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户角色
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER(0, "user"),
    ADMIN(1, "admin");

    @EnumValue
    private final Integer value;
    private final String text;

    public static UserRole of(Integer value) {
        for (UserRole role : UserRole.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        return USER;
    }
}

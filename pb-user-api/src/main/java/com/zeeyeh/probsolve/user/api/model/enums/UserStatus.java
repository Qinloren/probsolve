package com.zeeyeh.probsolve.user.api.model.enums;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户状态
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum UserStatus {

    PAUSE(0, "pause"),
    NORMAL(1, "normal");

    @EnumValue
    private final int code;
    private final String text;

    public static UserStatus of(int code) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.getCode() == code) {
                return userStatus;
            }
        }
        return NORMAL;
    }
}

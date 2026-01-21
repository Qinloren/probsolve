package com.zeeyeh.probsolve.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 统一响应码
 *
 * @author Qinloren
 */
@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    SUCCESS(0, "成功"),
    UNAUTHORIZED(31000, "无权限"),
    PARAM_ERROR(41000, "参数错误"),
    BUSINESS_ERROR(51000, "业务异常"),
    SYSTEM_ERROR(51001, "系统异常"),
    ;
    private final Integer code;
    private final String text;

    public static ResponseCode of(Integer code) {
        if (code == null) {
            return null;
        }
        for (ResponseCode errorCode : ResponseCode.values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return null;
    }
}

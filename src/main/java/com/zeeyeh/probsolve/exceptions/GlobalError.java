package com.zeeyeh.probsolve.exceptions;

public enum GlobalError {
    UNAUTHORIZED(1001),
    PARAMETER_ANOMALY(1002),
    SERVER_ERROR(1002),
    // user
    USER_NOT_FOUND(2001),
    USER_ALREADY_FOUND(2002),
    USER_CREATE_FAILED(2003),
    USER_LOGIN_FAILED(2004),
    USER_LOGOUT_FAILED(2005),
    USER_UPDATE_FAILED(2006),
    USER_DELETE_FAILED(2007),
    USER_NOT_LOGIN(2008),
    USER_PASSWORD_ERROR(2009),
    ;
    private final Integer code;

    GlobalError(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static GlobalError valueOf(Integer code) {
        for (GlobalError value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}

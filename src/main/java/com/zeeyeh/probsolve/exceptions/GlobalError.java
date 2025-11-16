package com.zeeyeh.probsolve.exceptions;

public enum GlobalError {
    UNAUTHORIZED(1001),
    PARAMETER_ANOMALY(1002),
    SERVER_ERROR(1002),
    // user
    USER_NOT_FOUND(1201),
    USER_ALREADY_FOUND(1202),
    USER_CREATE_FAILED(1203),
    USER_LOGIN_FAILED(1204),
    USER_LOGOUT_FAILED(1205),
    USER_UPDATE_FAILED(1206),
    USER_DELETE_FAILED(1207),
    USER_NOT_LOGIN(1208),
    USER_PASSWORD_ERROR(1209),
    // announcements
    ANNOUNCEMENT_ALREADY_FOUND(1301),
    ANNOUNCEMENT_NOT_FOUND(1302),
    ANNOUNCEMENT_CREATE_FAILED(1303),
    ANNOUNCEMENT_UPDATE_FAILED(1304),
    ANNOUNCEMENT_DELETE_FAILED(1305),
    // questions
    QUESTION_ALREADY_FOUND(1401),
    QUESTION_NOT_FOUND(1402),
    QUESTION_CREATE_FAILED(1403),
    QUESTION_UPDATE_FAILED(1404),
    QUESTION_DELETE_FAILED(1405),
    // question categories
    QUESTION_CATEGORY_ALREADY_FOUND(1501),
    QUESTION_CATEGORY_NOT_FOUND(1502),
    QUESTION_CATEGORY_CREATE_FAILED(1503),
    QUESTION_CATEGORY_UPDATE_FAILED(1504),
    QUESTION_CATEGORY_DELETE_FAILED(1505),
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

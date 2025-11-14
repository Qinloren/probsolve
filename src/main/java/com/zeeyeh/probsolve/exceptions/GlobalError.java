package com.zeeyeh.probsolve.exceptions;

public enum GlobalError {
    UNAUTHORIZED(1001),
    PARAMETER_ANOMALY(1002),
    SERVER_ERROR(1002),
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

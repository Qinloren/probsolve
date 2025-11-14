package com.zeeyeh.probsolve.exceptions;

public class ServiceException extends RuntimeException {
    private final Integer code;
    private final String message;

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(GlobalError error) {
        this.code = error.getCode();
        this.message = error.name();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

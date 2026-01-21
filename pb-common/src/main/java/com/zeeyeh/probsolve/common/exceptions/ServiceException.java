package com.zeeyeh.probsolve.common.exceptions;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author Qinloren
 */
@Getter
public class ServiceException extends RuntimeException {
    private final Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResponseCode responseCode) {
        super(responseCode.getText());
        this.code = responseCode.getCode();
    }

    public ServiceException(ResponseCode responseCode, String message) {
        super(message);
        this.code = responseCode.getCode();
    }
}

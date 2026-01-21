package com.zeeyeh.probsolve.common.response;

import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result<?> handlerServiceException(ServiceException e) {
        log.error("[业务异常] {}", e.getMessage());
        return Result.any(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handlerValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = ResponseCode.PARAM_ERROR.getText();
        if (fieldError != null && fieldError.getDefaultMessage() != null) {
            message = fieldError.getDefaultMessage();
        }
        log.error("[参数异常] {}", message);
        return Result.any(-1, message);
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handlerException(Exception e) {
        log.error("[未知异常] ", e);
        return Result.any(-1, ResponseCode.SYSTEM_ERROR.getText());
    }
}

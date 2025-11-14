package com.zeeyeh.probsolve.controller.response;

import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.provider.LangProvider;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    LangProvider langProvider;

    @ExceptionHandler
    private R<?> handlerServiceException(ServiceException e) {
        return R.any(e.getCode(), langProvider.translate(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> handlerValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = langProvider.translate(GlobalError.PARAMETER_ANOMALY.name());
        if (fieldError != null && fieldError.getDefaultMessage() != null) {
            message = langProvider.translate(fieldError.getDefaultMessage());
        }
        return R.any(-1, message);
    }

    @ExceptionHandler(Exception.class)
    public R<?> handlerException(Exception e) {
        logger.error("[未知异常] ", e);
        return R.any(-1, langProvider.translate(GlobalError.SERVER_ERROR.name()));
    }
}

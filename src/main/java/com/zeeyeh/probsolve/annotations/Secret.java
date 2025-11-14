package com.zeeyeh.probsolve.annotations;

import java.lang.annotation.*;

/**
 * 敏感数据注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Secret {
}

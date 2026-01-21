package com.zeeyeh.probsolve.common.annotations;

import java.lang.annotation.*;

/**
 * 敏感数据注解
 *
 * @author Qinloren
 */
@Target({
        ElementType.METHOD,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Secret {
}

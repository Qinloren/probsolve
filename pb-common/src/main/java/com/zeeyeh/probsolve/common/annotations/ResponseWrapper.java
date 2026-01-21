package com.zeeyeh.probsolve.common.annotations;

import java.lang.annotation.*;

/**
 * 统一返回结果包装
 *
 * @author Qinloren
 */
@Target({
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ResponseWrapper {
    /**
     * 是否返回时间戳
     * @return 是否返回时间戳
     */
    boolean timestamp() default true;
}

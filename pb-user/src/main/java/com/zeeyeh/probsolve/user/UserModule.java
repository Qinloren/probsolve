package com.zeeyeh.probsolve.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * 用户模块
 *
 * @author Qinloren
 */
@Component
@MapperScan("com.zeeyeh.probsolve.user.mapper")
public class UserModule {
}

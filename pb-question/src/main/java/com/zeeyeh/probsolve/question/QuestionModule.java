package com.zeeyeh.probsolve.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * 题目模块
 *
 * @author Qinloren
 */
@Component
@MapperScan("com.zeeyeh.probsolve.question.mapper")
public class QuestionModule {
}

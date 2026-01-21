package com.zeeyeh.probsolve.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Qinloren
 */
@SpringBootApplication(scanBasePackages = "com.zeeyeh.probsolve")
public class ProbsolveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProbsolveApplication.class, args);
    }
}

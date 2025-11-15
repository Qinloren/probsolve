package com.zeeyeh.probsolve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zeeyeh.probsolve.mapper")
public class ProbsolveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProbsolveApplication.class, args);
    }

}

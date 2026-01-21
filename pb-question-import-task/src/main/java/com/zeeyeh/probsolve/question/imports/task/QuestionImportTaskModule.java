package com.zeeyeh.probsolve.question.imports.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * 题目导入任务模块
 *
 * @author Qinloren
 */
@Component
@MapperScan("com.zeeyeh.probsolve.question.imports.task.mapper")
public class QuestionImportTaskModule {
}

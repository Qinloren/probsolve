package com.zeeyeh.probsolve.question.imports.task.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zeeyeh.probsolve.question.imports.task.api.model.entity.QuestionImportTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题导入任务表 映射层
 *
 * @author Qinloren
 */
@Mapper
public interface QuestionImportTaskMapper extends BaseMapper<QuestionImportTask> {
}

package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.ExamQuestionRelation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试-题目关联表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ExamQuestionRelationService extends IService<ExamQuestionRelation> {

}

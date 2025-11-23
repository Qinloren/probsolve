package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.PracticeRecords;
import org.springframework.transaction.annotation.Transactional;

/**
 * 练习记录表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface PracticeRecordsService extends IService<PracticeRecords> {

}

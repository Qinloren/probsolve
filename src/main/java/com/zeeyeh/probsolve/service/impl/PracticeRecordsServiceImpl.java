package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.PracticeRecords;
import com.zeeyeh.probsolve.mapper.PracticeRecordsMapper;
import com.zeeyeh.probsolve.service.PracticeRecordsService;
import org.springframework.stereotype.Service;

/**
 * 练习记录表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class PracticeRecordsServiceImpl extends ServiceImpl<PracticeRecordsMapper, PracticeRecords>  implements PracticeRecordsService{

}

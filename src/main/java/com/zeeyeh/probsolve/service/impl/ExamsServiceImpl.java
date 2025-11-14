package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.data.Exams;
import com.zeeyeh.probsolve.mapper.ExamsMapper;
import com.zeeyeh.probsolve.service.ExamsService;
import org.springframework.stereotype.Service;

/**
 * 考试表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class ExamsServiceImpl extends ServiceImpl<ExamsMapper, Exams>  implements ExamsService{

}

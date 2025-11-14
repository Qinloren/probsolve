package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.ErrorBooks;
import com.zeeyeh.probsolve.mapper.ErrorBooksMapper;
import com.zeeyeh.probsolve.service.ErrorBooksService;
import org.springframework.stereotype.Service;

/**
 * 错题本 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class ErrorBooksServiceImpl extends ServiceImpl<ErrorBooksMapper, ErrorBooks>  implements ErrorBooksService{

}

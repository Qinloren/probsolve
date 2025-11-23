package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.ErrorBooks;
import org.springframework.transaction.annotation.Transactional;

/**
 * 错题本 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ErrorBooksService extends IService<ErrorBooks> {

}

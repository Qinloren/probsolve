package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.entity.data.ThirdPartyLogins;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方登录表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface ThirdPartyLoginsService extends IService<ThirdPartyLogins> {

}

package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.ThirdPartyLogins;
import com.zeeyeh.probsolve.mapper.ThirdPartyLoginsMapper;
import com.zeeyeh.probsolve.service.ThirdPartyLoginsService;
import org.springframework.stereotype.Service;

/**
 * 第三方登录表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class ThirdPartyLoginsServiceImpl extends ServiceImpl<ThirdPartyLoginsMapper, ThirdPartyLogins>  implements ThirdPartyLoginsService{

}

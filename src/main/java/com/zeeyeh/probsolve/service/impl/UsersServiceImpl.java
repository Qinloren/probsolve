package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.entity.Users;
import com.zeeyeh.probsolve.mapper.UsersMapper;
import com.zeeyeh.probsolve.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>  implements UsersService{

}

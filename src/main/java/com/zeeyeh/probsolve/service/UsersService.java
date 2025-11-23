package com.zeeyeh.probsolve.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.dto.users.UserCreateDto;
import com.zeeyeh.probsolve.dto.users.UserLoginDto;
import com.zeeyeh.probsolve.dto.users.UserRegisterDto;
import com.zeeyeh.probsolve.dto.users.UserSearchDto;
import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.entity.data.Users;
import com.zeeyeh.probsolve.vo.basic.UserVo;
import com.zeeyeh.probsolve.vo.search.UserSearchVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 服务层。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Transactional(rollbackFor = Exception.class)
public interface UsersService extends IService<Users>, UserDetailsService {

    /**
     * 创建用户
     * @param createDto 创建参数
     * @return 创建用户信息
     */
    UserVo create(UserCreateDto createDto);

    /**
     * 注册用户
     * @param registerDto 注册参数
     * @return 注册用户信息
     */
    UserVo register(UserRegisterDto registerDto);

    /**
     * 登录用户
     * @param loginDto 登录参数
     * @return 登录用户信息
     */
    R<UserVo> login(UserLoginDto loginDto);

    /**
     * 搜索用户
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    UserSearchVo search(UserSearchDto searchDto);

    /**
     * 获取用户详情
     * @param id 用户id
     * @return 用户详情
     */
    UserVo detail(long id);

    /**
     * 验证用户
     * @param request 请求
     * @return 验证结果
     */
    R<Boolean> validate(HttpServletRequest request);
}

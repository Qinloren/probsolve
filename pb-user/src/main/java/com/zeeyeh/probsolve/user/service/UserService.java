package com.zeeyeh.probsolve.user.service;

import com.mybatisflex.core.service.IService;
import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.user.api.model.dto.UserCreateDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserLoginDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserRegisterDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserSearchDto;
import com.zeeyeh.probsolve.user.api.model.entity.User;
import com.zeeyeh.probsolve.user.api.model.vo.UserSearchVo;
import com.zeeyeh.probsolve.user.api.model.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 服务层
 *
 * @author Qinloren
 */
@Transactional(rollbackFor = Exception.class)
public interface UserService extends IService<User>, UserDetailsService {

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
    Result<UserVo> login(UserLoginDto loginDto);

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
    Result<Boolean> validate(HttpServletRequest request);
}

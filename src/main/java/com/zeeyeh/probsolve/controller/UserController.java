package com.zeeyeh.probsolve.controller;

import com.zeeyeh.probsolve.dto.users.UserCreateDto;
import com.zeeyeh.probsolve.dto.users.UserLoginDto;
import com.zeeyeh.probsolve.dto.users.UserRegisterDto;
import com.zeeyeh.probsolve.dto.users.UserSearchDto;
import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.service.UsersService;
import com.zeeyeh.probsolve.vo.basic.UserVo;
import com.zeeyeh.probsolve.vo.search.UserSearchVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 用户表 控制层。
 */
@RestController
@RequestMapping("sys/user")
public class UserController {

    @Resource
    UsersService usersService;

    /**
     * 创建用户接口
     * @param createDto 创建用户参数
     * @return 创建用户结果
     */
    @PostMapping("create")
    @ResponseBody
    public UserVo create(@RequestBody UserCreateDto createDto) {
        return usersService.create(createDto);
    }

    /**
     * 注册接口
     * @param registerDto 注册参数
     * @return 注册结果
     */
    @PostMapping("register")
    @ResponseBody
    public UserVo register(@RequestBody UserRegisterDto registerDto) {
        return usersService.register(registerDto);
    }

    /**
     * 登录接口
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @PostMapping("login")
    @ResponseBody
    public R<UserVo> login(@RequestBody UserLoginDto loginDto) {
        return usersService.login(loginDto);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public UserSearchVo search(UserSearchDto searchDto) {
        return usersService.search(searchDto);
    }

    /**
     * 用户详情接口
     * @param id 用户id
     * @return 用户详情
     */
    @GetMapping("detail")
    @ResponseBody
    public UserVo detail(@RequestParam long id) {
        return usersService.detail(id);
    }
}

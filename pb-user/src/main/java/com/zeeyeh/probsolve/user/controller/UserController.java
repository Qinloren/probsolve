package com.zeeyeh.probsolve.user.controller;

import com.zeeyeh.probsolve.common.annotations.ResponseWrapper;
import com.zeeyeh.probsolve.common.annotations.Secret;
import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.user.api.model.dto.UserCreateDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserLoginDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserRegisterDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserSearchDto;
import com.zeeyeh.probsolve.user.api.model.vo.UserSearchVo;
import com.zeeyeh.probsolve.user.api.model.vo.UserVo;
import com.zeeyeh.probsolve.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author Qinloren
 */
@RestController
@RequestMapping("sys/user")
@ResponseWrapper
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建用户接口
     * @param createDto 创建用户参数
     * @return 创建用户结果
     */
    @PostMapping("create")
    @ResponseBody
    public UserVo create(@RequestBody UserCreateDto createDto) {
        return userService.create(createDto);
    }

    /**
     * 注册接口
     * @param registerDto 注册参数
     * @return 注册结果
     */
    @PostMapping("register")
    @ResponseBody
    @Secret
    public UserVo register(@RequestBody UserRegisterDto registerDto) {
        return userService.register(registerDto);
    }

    /**
     * 登录接口
     * @param loginDto 登录参数
     * @return 登录结果
     */
    @PostMapping("login")
    @ResponseBody
    public Result<UserVo> login(@RequestBody UserLoginDto loginDto) {
        return userService.login(loginDto);
    }

    /**
     * 搜索接口
     * @param searchDto 搜索参数
     * @return 搜索结果
     */
    @GetMapping("search")
    @ResponseBody
    public UserSearchVo search(UserSearchDto searchDto) {
        return userService.search(searchDto);
    }

    /**
     * 用户详情接口
     * @param id 用户 id
     * @return 用户详情
     */
    @GetMapping("detail")
    @ResponseBody
    // @Secret
    public UserVo detail(@RequestParam long id) {
        return userService.detail(id);
    }

    @GetMapping("validate")
    @ResponseBody
    public Result<Boolean> validate(HttpServletRequest request) {
        return userService.validate(request);
    }
}

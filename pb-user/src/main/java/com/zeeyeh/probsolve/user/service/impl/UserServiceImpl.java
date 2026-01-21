package com.zeeyeh.probsolve.user.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.common.provider.RedisProvider;
import com.zeeyeh.probsolve.common.provider.TokenProvider;
import com.zeeyeh.probsolve.user.api.model.dto.UserCreateDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserLoginDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserRegisterDto;
import com.zeeyeh.probsolve.user.api.model.dto.UserSearchDto;
import com.zeeyeh.probsolve.user.api.model.entity.User;
import com.zeeyeh.probsolve.user.api.model.enums.UserRole;
import com.zeeyeh.probsolve.user.api.model.enums.UserStatus;
import com.zeeyeh.probsolve.user.api.model.vo.UserSearchVo;
import com.zeeyeh.probsolve.user.api.model.vo.UserVo;
import com.zeeyeh.probsolve.user.mapper.UserMapper;
import com.zeeyeh.probsolve.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * UserService 实现类
 *
 * @author Qinloren
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisProvider redisProvider;
    @Value("${app.token.expire.value}")
    private Long tokenExpire;

    @Value("${app.token.expire.persistent}")
    private Long tokenExpirePersistent;

    public UserServiceImpl(PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RedisProvider redisProvider) {
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.redisProvider = redisProvider;
    }

    @Override
    public UserVo create(UserCreateDto createDto) {
        String username = createDto.getUsername();
        if (this.exists(new QueryWrapper().eq(User::getUsername, username))) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "用户已存在");
        }
        User users = new User();
        users.setUsername(createDto.getUsername());
        users.setEmail(createDto.getEmail());
        users.setPassword(passwordEncoder.encode(createDto.getPassword()));
        users.setRole(createDto.getRole());
        users.setStatus(createDto.getStatus());
        users.setAvatar(createDto.getAvatar());
        users.setTotalScore(createDto.getTotalScore());
        users.setLevel(createDto.getLevel());
        users.setCreateTime(LocalDateTime.now());
        users.setUpdateTime(LocalDateTime.now());
        users.setLoginTime(LocalDateTime.now());
        if (!this.save(users)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "创建失败");
        }
        users = this.getOne(new QueryWrapper().eq(User::getUsername, createDto.getUsername()));
        return UserVo.of(users);
    }

    @Override
    public UserVo register(UserRegisterDto registerDto) {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername(registerDto.getUsername());
        userCreateDto.setEmail(registerDto.getEmail());
        userCreateDto.setRole(UserRole.USER);
        userCreateDto.setStatus(UserStatus.NORMAL);
        userCreateDto.setAvatar(registerDto.getAvatar());
        userCreateDto.setPassword(registerDto.getPassword());
        return create(userCreateDto);
    }

    @Override
    public Result<UserVo> login(UserLoginDto loginDto) {
        // 构建搜索用户条件
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.hasText(loginDto.getUsername())) {
            queryWrapper.eq(User::getUsername, loginDto.getUsername());
        } else if (StringUtils.hasText(loginDto.getEmail())) {
            queryWrapper.eq(User::getEmail, loginDto.getEmail());
        } else {
            throw new ServiceException(ResponseCode.PARAM_ERROR);
        }
        // 验证用户是否存在
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "用户不存在");
        }
        // 验证密码
        User users = this.getOne(queryWrapper);
        if (!passwordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "密码错误");
        }
        // 更新登录时间
        users.setLoginTime(LocalDateTime.now());
        if (!this.update(users, queryWrapper)) {
            throw new ServiceException(ResponseCode.BUSINESS_ERROR, "登录失败");
        }
        // 获取 token
        long expire = tokenExpire;
        if (loginDto.isPersistent()) {
            expire = tokenExpirePersistent;
        }
        String token = "Bearer " + tokenProvider.createToken(Map.of(
                "id", users.getId(),
                "username", users.getUsername(),
                "email", users.getEmail()
        ), expire);
        redisProvider.set("token:user:" + users.getId(), token, expire, TimeUnit.SECONDS);
        return Result.success(UserVo.of(users), Map.of(
                "Authorization",
                token
        ));
    }

    @Override
    public UserSearchVo search(UserSearchDto searchDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Optional.ofNullable(searchDto.getUsername())
                .ifPresent(username -> queryWrapper.eq(User::getUsername, username));
        Optional.ofNullable(searchDto.getEmail())
                .ifPresent(email -> queryWrapper.eq(User::getEmail, email));
        Optional.ofNullable(searchDto.getRole())
                .ifPresent(role -> queryWrapper.eq(User::getRole, role));
        Optional.ofNullable(searchDto.getStatus())
                .ifPresent(status -> queryWrapper.eq(User::getStatus, status));
        Optional.ofNullable(searchDto.getTotalScore())
                .ifPresent(totalScore -> queryWrapper.eq(User::getTotalScore, totalScore));
        Optional.ofNullable(searchDto.getLevel())
                .ifPresent(level -> queryWrapper.eq(User::getLevel, level));
        Page<User> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<User> usersPage = this.page(page, queryWrapper);
        List<UserVo> list = usersPage.getRecords().stream()
                .map(UserVo::of)
                .toList();
        return new UserSearchVo(
                list,
                page.getTotalPage(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(User::getUsername, username);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "用户不存在");
        }
        return this.getOne(queryWrapper);
    }

    @Override
    public UserVo detail(long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(User::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "用户不存在");
        }
        User users = this.getOne(queryWrapper);
        return UserVo.of(users);
    }

    @Override
    public Result<Boolean> validate(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(header)) {
            boolean verified = tokenProvider.verifyToken(header);
            if (!verified) {
                throw new ServiceException(ResponseCode.UNAUTHORIZED);
            }
            Long id = tokenProvider.getClaim(header, "id").asLong();
            if (!redisProvider.has("token:user:" + id)) {
                throw new ServiceException(ResponseCode.UNAUTHORIZED);
            }
            String saveToken = redisProvider.get("token:user:" + id);
            if (!saveToken.equals(header)) {
                throw new ServiceException(ResponseCode.UNAUTHORIZED);
            }
            return Result.success(verified);
        }
        throw new ServiceException(ResponseCode.UNAUTHORIZED);
    }
}

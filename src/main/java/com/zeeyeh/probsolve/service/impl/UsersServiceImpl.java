package com.zeeyeh.probsolve.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zeeyeh.probsolve.dto.users.UserCreateDto;
import com.zeeyeh.probsolve.dto.users.UserLoginDto;
import com.zeeyeh.probsolve.dto.users.UserRegisterDto;
import com.zeeyeh.probsolve.dto.users.UserSearchDto;
import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.entity.data.Users;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.mapper.UsersMapper;
import com.zeeyeh.probsolve.provider.RedisProvider;
import com.zeeyeh.probsolve.provider.TokenProvider;
import com.zeeyeh.probsolve.service.UsersService;
import com.zeeyeh.probsolve.vo.basic.UserVo;
import com.zeeyeh.probsolve.vo.search.UserSearchVo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户表 服务层实现。
 *
 * @author Qinloren
 * @since 1.0.0
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>  implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RedisProvider redisProvider;
    @Value("${app.token.expire.value}")
    private Long tokenExpire;

    @Value("${app.token.expire.persistent}")
    private Long tokenExpirePersistent;

    public UsersServiceImpl(PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RedisProvider redisProvider) {
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.redisProvider = redisProvider;
    }

    // @Resource
    // PasswordEncoder passwordEncoder;

    // @Resource
    // TokenProvider tokenProvider;

    // @Resource
    // RedisProvider redisProvider;

    @Override
    public UserVo create(UserCreateDto createDto) {
        String username = createDto.getUsername();
        if (this.exists(new QueryWrapper().eq(Users::getUsername, username))) {
            throw new ServiceException(GlobalError.USER_ALREADY_FOUND);
        }
        Users users = new Users();
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
        if (!this.save(users)) {
            throw new ServiceException(GlobalError.USER_CREATE_FAILED);
        }
        users = this.getOne(new QueryWrapper().eq(Users::getUsername, createDto.getUsername()));
        return UserVo.of(users);
    }

    @Override
    public UserVo register(UserRegisterDto registerDto) {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername(registerDto.getUsername());
        userCreateDto.setEmail(registerDto.getEmail());
        userCreateDto.setAvatar(registerDto.getAvatar());
        userCreateDto.setPassword(registerDto.getPassword());
        return create(userCreateDto);
    }

    @Override
    public R<UserVo> login(UserLoginDto loginDto) {
        // 构建搜索用户条件
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.hasText(loginDto.getUsername())) {
            queryWrapper.eq(Users::getUsername, loginDto.getUsername());
        } else if (StringUtils.hasText(loginDto.getEmail())) {
            queryWrapper.eq(Users::getEmail, loginDto.getEmail());
        } else {
            throw new ServiceException(GlobalError.PARAMETER_ANOMALY);
        }
        // 验证用户是否存在
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.USER_NOT_FOUND);
        }
        // 验证密码
        Users users = this.getOne(queryWrapper);
        if (!passwordEncoder.matches(loginDto.getPassword(), users.getPassword())) {
            throw new ServiceException(GlobalError.USER_PASSWORD_ERROR);
        }
        // 更新登录时间
        users.setLastLoginTime(LocalDateTime.now());
        if (!this.update(users, queryWrapper)) {
            throw new ServiceException(GlobalError.USER_LOGIN_FAILED);
        }
        // 获取token
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
        return R.success(UserVo.of(users), Map.of(
                "Authorization",
                token
        ));
    }

    @Override
    public UserSearchVo search(UserSearchDto searchDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.hasText(searchDto.getUsername())) {
            queryWrapper.eq(Users::getUsername, searchDto.getUsername());
        }
        if (StringUtils.hasText(searchDto.getEmail())) {
            queryWrapper.eq(Users::getEmail, searchDto.getEmail());
        }
        if (StringUtils.hasText(searchDto.getRole())) {
            queryWrapper.eq(Users::getRole, searchDto.getRole());
        }
        if (StringUtils.hasText(searchDto.getStatus())) {
            queryWrapper.eq(Users::getStatus, searchDto.getStatus());
        }
        if (StringUtils.hasText(searchDto.getTotalScore())) {
            queryWrapper.eq(Users::getTotalScore, searchDto.getTotalScore());
        }
        if (StringUtils.hasText(searchDto.getLevel())) {
            queryWrapper.eq(Users::getLevel, searchDto.getLevel());
        }
        Page<Users> page = new Page<>(searchDto.getPage(), searchDto.getPageSize());
        Page<Users> usersPage = this.page(page, queryWrapper);
        List<UserVo> list = usersPage.getRecords().stream()
                .map(UserVo::of)
                .toList();
        return new UserSearchVo(
                list,
                page.getTotalRow(),
                page.getPageNumber(),
                page.getPageSize()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Users::getUsername, username);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.USER_NOT_FOUND);
        }
        return this.getOne(queryWrapper);
    }

    @Override
    public UserVo detail(long id) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq(Users::getId, id);
        if (!this.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.USER_NOT_FOUND);
        }
        Users users = this.getOne(queryWrapper);
        return UserVo.of(users);
    }
}

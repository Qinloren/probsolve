package com.zeeyeh.probsolve.user.config;


import com.alibaba.fastjson2.JSON;
import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.user.config.handler.LogoutSuccessHandlerImpl;
import com.zeeyeh.probsolve.user.filter.SecurityFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置类
 *
 * @author Qinloren
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    SecurityFilter securityFilter;
    @Resource
    LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                                    "/sys/user/login",
                                    "/sys/user/register"
                            ).permitAll()
                            .requestMatchers(
                                    HttpMethod.GET
                            ).permitAll()
                            .anyRequest().authenticated();
                })
                .headers(headers -> headers.cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .logout(logout -> logout.logoutUrl("/sys/user/logout")
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(authenticationEntryPoint());
                })
                .build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            log.error("认证失败，URI: {}, Method: {}, Exception: {}",
                    request.getRequestURI(),
                    request.getMethod(),
                    authException.getMessage(),
                    authException);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset-utf-8");
            response.getWriter().write(JSON.toJSONString(Result.any(ResponseCode.UNAUTHORIZED.getCode(), "无权限")));
        };
    }
}

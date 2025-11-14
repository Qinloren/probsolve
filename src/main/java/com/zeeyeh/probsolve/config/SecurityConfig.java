package com.zeeyeh.probsolve.config;

import com.alibaba.fastjson2.JSON;
import com.zeeyeh.probsolve.entity.R;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.filter.SecurityFilter;
import com.zeeyeh.probsolve.config.handler.LogoutSuccessHandlerImpl;
import com.zeeyeh.probsolve.provider.LangProvider;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Resource
    LangProvider langProvider;
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
                    .requestMatchers(
                            HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/**.html",
                            "/**.css",
                            "/**.js"
                    ).permitAll()
                    .requestMatchers(
                            "/error",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/webjars/**"
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
            logger.error("认证失败，URI: {}, Method: {}, Exception: {}",
                    request.getRequestURI(),
                    request.getMethod(),
                    authException.getMessage(),
                    authException);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset-utf-8");
            response.getWriter().write(JSON.toJSONString(R.any(GlobalError.UNAUTHORIZED.getCode(), langProvider.translate(GlobalError.UNAUTHORIZED.name()))));
        };
    }
}

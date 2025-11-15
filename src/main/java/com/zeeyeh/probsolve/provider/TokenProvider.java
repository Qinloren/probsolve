package com.zeeyeh.probsolve.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
/**
 * TokenProvider 类用于生成、验证和管理基于 JWT 的 Token。
 * 使用 HMAC256 算法签名，并支持 Token 刷新、过期判断等功能。
 */
public class TokenProvider {
    /**
     * Token 过期时间（单位：秒），从配置文件中读取。
     */
    @Value("${app.token.expire.value}")
    private long EXPIRE_TIME;

    /**
     * Token 签名密钥，从配置文件中读取。
     */
    @Value("${app.token.secret}")
    private String SECRET;

    /**
     * 根据传入的负载信息创建一个新的 JWT Token。
     *
     * @param payload 包含要放入 Token 中的数据键值对
     * @return 生成的 JWT Token 字符串
     */
    public String createToken(Map<String, Object> payload) {
        return createToken(payload, EXPIRE_TIME);
    }

    /**
     * 根据传入的负载信息创建一个新的 JWT Token，并设置过期时间。
     * @param payload 负载数据
     * @param expireTime 过期时间（秒）
     * @return 生成的 JWT Token 字符串
     */
    public String createToken(Map<String, Object> payload, long expireTime) {
        // 构建 JWT Token 并设置负载数据
        JWTCreator.Builder builder = JWT.create();
        payload.forEach((key, value) -> {
            if (value instanceof String) {
                builder.withClaim(key, (String) value);
            } else if (value instanceof Integer) {
                builder.withClaim(key, (Integer) value);
            } else if (value instanceof Long) {
                builder.withClaim(key, (Long) value);
            } else if (value instanceof Boolean) {
                builder.withClaim(key, (Boolean) value);
            } else {
                builder.withClaim(key, value.toString());
            }
        });

        // 设置 Token 过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, (int) expireTime);
        builder.withExpiresAt(instance.getTime());

        // 使用指定算法签名并返回 Token
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证给定的 Token 是否有效。
     *
     * @param token 待验证的 Token 字符串
     * @return 如果 Token 合法则返回 true，否则返回 false
     */
    public boolean verifyToken(String token) {
        try {
            decodedToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查 Token 是否已过期。
     *
     * @param token 待检查的 Token 字符串
     * @return 如果 Token 已过期则返回 true，否则返回 false
     */
    public boolean isTokenExpired(String token) {
        try {
            DecodedJWT verify = decodedToken(token);
            return verify.getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 根据旧 Token 创建一个新 Token，保留原负载内容但更新过期时间。
     *
     * @param oldToken 原始 Token 字符串
     * @return 新生成的 Token 字符串
     * @throws RuntimeException 当刷新过程出现异常时抛出
     */
    public String refreshToken(String oldToken) {
        try {
            // 解析原始 Token 获取负载信息
            DecodedJWT decodedJWT = decodedToken(oldToken);
            Map<String, Claim> claims = decodedJWT.getClaims();
            Map<String, Object> payload = new HashMap<>();

            // 将 Claims 转换为通用对象映射
            claims.forEach((key, value) -> {
                if (value.asString() != null) {
                    payload.put(key, value.asString());
                } else if (value.asBoolean() != null) {
                    payload.put(key, value.asBoolean());
                } else if (value.asInt() != null) {
                    payload.put(key, value.asInt());
                } else if (value.asLong() != null) {
                    payload.put(key, value.asLong());
                } else if (value.asDouble() != null) {
                    payload.put(key, value.asDouble());
                } else if (value.asDate() != null) {
                    payload.put(key, value.asDate());
                } else {
                    payload.put(key, value.asString());
                }
            });

            // 使用新的过期时间重新生成 Token
            return createToken(payload);
        } catch (Exception e) {
            throw new RuntimeException("刷新Token失败", e);
        }
    }

    /**
     * 对 Token 进行解码操作。
     *
     * @param token 待解码的 Token 字符串
     * @return 解码后的 DecodedJWT 对象
     */
    public DecodedJWT decodedToken(String token) {
        token = token.startsWith("Bearer ") ? token.substring(6).trim() : token;
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 获取 Token 中特定名称的字段值。
     *
     * @param token     Token 字符串
     * @param claimName 声明字段名
     * @return 对应的 Claim 对象
     */
    public Claim getClaim(String token, String claimName) {
        DecodedJWT decodedJWT = decodedToken(token);
        return decodedJWT.getClaim(claimName);
    }

    /**
     * 获取 Token 的过期时间。
     *
     * @param token Token 字符串
     * @return Token 的过期日期
     */
    public Date getExpireationDate(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        return decodedJWT.getExpiresAt();
    }

    /**
     * 验证 Token 并在失败时抛出自定义运行时异常。
     *
     * @param token 待验证的 Token 字符串
     * @return 成功验证后返回 DecodedJWT 对象
     * @throws RuntimeException 如果 Token 已过期或无效，则抛出对应异常
     */
    public DecodedJWT verifyAndGetToken(String token) {
        try {
            return decodedToken(token);
        } catch (TokenExpiredException e) {
            throw new RuntimeException("Token 已过期", e);
        } catch (Exception e) {
            throw new RuntimeException("Token 验证失败", e);
        }
    }
}

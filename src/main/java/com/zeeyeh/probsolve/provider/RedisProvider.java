package com.zeeyeh.probsolve.provider;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
/**
 * 用于提供基于 Redis 的令牌操作功能。
 * 包括设置、获取、删除键值对，以及实现分布式锁的功能。
 */
@Component
public class RedisProvider {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置指定 key 的值
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置指定 key 的值，并指定过期时间
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key 键
     * @return 返回key对应的字符串值，如果key不存在则返回null
     */
    public String get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : o.toString();
    }

    /**
     * 删除指定的 key
     *
     * @param key 要删除的键
     * @return 如果key存在并且被成功删除则返回true，否则返回false
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断指定的 key 是否存在
     *
     * @param key 要检查的键
     * @return 如果key存在则返回true，否则返回false
     */
    public Boolean has(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 尝试获取分布式锁（默认时间单位为秒）
     *
     * @param lockKey   锁的键
     * @param requestId 请求标识符，用于区分不同的锁持有者
     * @param seconds   锁的超时时间（秒）
     * @return 如果获取锁成功则返回true，否则返回false
     */
    public Boolean tryLock(String lockKey, String requestId, long seconds) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, seconds, TimeUnit.SECONDS);
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey   锁的键
     * @param requestId 请求标识符，用于区分不同的锁持有者
     * @param seconds   锁的超时时间
     * @param unit      时间单位
     * @return 如果获取锁成功则返回true，否则返回false
     */
    public Boolean tryLock(String lockKey, String requestId, long seconds, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, seconds, unit);
    }

    /**
     * 释放分布式锁
     * 使用 Lua 脚本确保解锁操作的原子性：只有当锁的持有者是当前请求方时才允许删除锁
     *
     * @param lockKey   锁的键
     * @param requestId 请求标识符，必须与加锁时使用的标识符一致
     * @return 如果成功释放锁则返回true，否则返回false
     */
    public Boolean releaseLock(String lockKey, String requestId) {
        // 定义Lua脚本：比较并删除
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) " +
                "else " +
                "return 0 " +
                "end");
        script.setResultType(Long.class);
        Long result = redisTemplate.execute(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return result.equals(1L);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }
}


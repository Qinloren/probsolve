package com.zeeyeh.probsolve.common.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson2.JSON;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * 加密工具类
 *
 * @author Qinloren
 */
public class SecretUtil {

    /**
     * 加密
     * @param data 待加密数据
     * @param key 密钥
     * @param iv 向量
     * @return 加密后的数据
     */
    public static String enc(Object data, byte[] key, byte[] iv) {
        AES aes = new AES(Mode.CFB, Padding.ZeroPadding, key, iv);
        return aes.encryptHex(data.toString()).toUpperCase(Locale.ROOT);
    }

    /**
     * RSA 加密
     * @param data 待加密数据
     * @param publicKey 公钥
     * @param privateKey 私钥
     * @return 加密后的数据
     */
    public static String encRsa(String data, String publicKey, String privateKey) {
        RSA rsa = new RSA(privateKey, publicKey);
        return rsa.encryptHex(data, KeyType.PrivateKey).toUpperCase(Locale.ROOT);
    }

    /**
     * 获取 sign
     * @param dataObject 数据
     * @return sign
     */
    public static String getSign(Object dataObject) {
        String data = JSON.toJSONString(dataObject);
        long timeMillis = System.currentTimeMillis();
        double vision = timeMillis / 1e3;
        long salt = (long) vision;
        salt = salt * 1713569335L + 6424747134L;
        salt = salt ^ (salt >> 32);
        salt = salt * 37686082L;
        salt = salt ^ (salt >> 16);
        salt = salt * 14711772L;
        salt = Math.abs(salt) % 1000000000000000000L;
        String saltString = String.valueOf(salt);
        data = data + saltString;
        return SecureUtil.md5(data);
    }

    /**
     * 生成随机密钥
     * @return 随机密钥
     */
    public static byte[] generateKey() {
        return HexUtil.encodeHexStr(generateBytes(8)).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成随机向量
     * @return 随机向量
     */
    public static byte[] generateIv() {
        return HexUtil.encodeHexStr(generateBytes(8)).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成指定长度的随机字节数组
     * @param length 字节数组长度
     * @return 随机字节数组
     */
    private static byte[] generateBytes(int length) {
        byte[] key = new byte[length];
        new SecureRandom().nextBytes(key);
        return key;
    }
}

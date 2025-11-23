package com.zeeyeh.probsolve.questions;

/**
 * 题库文件处理器，
 * 解析并上传数据到服务器
 */
public interface QuestionLibFileHandler {

    /**
     * 处理文件
     * @param bytes 文件字节数组
     * @param uid 用户id
     * @return 处理结果
     */
    boolean handler(byte[] bytes, Long uid);

    /**
     * 判断是否支持该文件类型
     * @param type 文件类型
     * @return 是否支持
     */
    default boolean supports(String type) {
        return getType().equalsIgnoreCase(type);
    }

    /**
     * 获取文件类型
     * @return 文件类型
     */
    String getType();
}

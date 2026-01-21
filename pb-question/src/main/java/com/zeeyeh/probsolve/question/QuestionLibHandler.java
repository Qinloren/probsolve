package com.zeeyeh.probsolve.question;

import java.io.InputStream;

/**
 * 题库处理器
 *
 * @author Qinloren
 */
public interface QuestionLibHandler {

    /**
     * 处理文件
     *
     * @param inputStream 文件输入流
     * @param taskId      任务Id
     * @param uid         用户id
     * @return 处理结果
     */
    boolean handler(InputStream inputStream, String taskId, Long uid);

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

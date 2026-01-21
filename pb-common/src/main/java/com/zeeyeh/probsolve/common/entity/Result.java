package com.zeeyeh.probsolve.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zeeyeh.probsolve.common.annotations.Secret;
import lombok.Getter;

import java.util.Map;

/**
 * 统一响应结果
 *
 * @author Qinloren
 */
@Getter
public class Result<T> {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    @Secret
    private String message;
    /**
     * 响应时间
     */
    private long timestamp;
    /**
     * 响应数据
     */
    @Secret
    private T data;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, Object> headers;

    public static <T> Result<T> success() {
        return any(0, "success", null, null);
    }

    public static <T> Result<T> success(T data) {
        return any(0, "success", null, data);
    }

    public static <T> Result<T> success(T data, Map<String, Object> header) {
        return any(0, "success", header, data);
    }

    public static <T> Result<T> any(int code, T data) {
        return any(code, "success", null, data);
    }

    public static <T> Result<T> any(int code, String message) {
        return any(code, message, null);
    }

    public static <T> Result<T> any(int code, String message, T data) {
        return any(code, message, null, data);
    }

    public static <T> Result<T> any(int code, String message, Map<String, Object> header) {
        return any(code, message, header, null);
    }

    public static <T> Result<T> any(int code, String message, Map<String, Object> header, T data) {
        // return Result.<T>builder()
        //         .code(code)
        //         .message(message)
        //         .timestamp(System.currentTimeMillis())
        //         .data(data)
        //         .headers(header)
        //         .build();
        return new Result<T>(message, code, System.currentTimeMillis(), data, header);
    }

    public Result() {
    }

    public Result(String message, int code, long timestamp, T data, Map<String, Object> headers) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
        this.headers = headers;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result<T> setHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }
}

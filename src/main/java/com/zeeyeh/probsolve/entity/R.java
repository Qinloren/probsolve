package com.zeeyeh.probsolve.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zeeyeh.probsolve.annotations.Secret;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "统一响应结果", hidden = true)
public class R<T> {
    @Schema(name = "code", description = "响应码")
    private int code;
    @Schema(name = "message", description = "响应信息")
    private String message;
    @Schema(name = "timestamp", description = "响应时间")
    private long timestamp;
    @Schema(name = "data", description = "响应数据")
    @Secret
    private T data;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(hidden = true)
    private Map<String, Object> headers;

    public static <T> R<T> success() {
        return any(0, "success", null, null);
    }

    public static <T> R<T> success(T data) {
        return any(0, "success", null, data);
    }

    public static <T> R<T> success(T data, Map<String, Object> header) {
        return any(0, "success", header, data);
    }

    public static <T> R<T> any(int code, T data) {
        return any(code, "success", null, data);
    }

    public static <T> R<T> any(int code, String message) {
        return any(code, message, null);
    }

    public static <T> R<T> any(int code, String message, T data) {
        return any(code, message, null, data);
    }

    public static <T> R<T> any(int code, String message, Map<String, Object> header) {
        return any(code, message, header, null);
    }

    public static <T> R<T> any(int code, String message, Map<String, Object> header, T data) {
        // return R.<T>builder()
        //         .code(code)
        //         .message(message)
        //         .timestamp(System.currentTimeMillis())
        //         .data(data)
        //         .headers(header)
        //         .build();
        return new R<T>(message, code, System.currentTimeMillis(), data, header);
    }

    public R() {
    }

    public R(String message, int code, long timestamp, T data, Map<String, Object> headers) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
        this.headers = headers;
    }

    public int getCode() {
        return code;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public R<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public T getData() {
        return data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public R<T> setHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }
}

package com.zeeyeh.probsolve.common.response;

import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson2.JSONObject;
import com.zeeyeh.probsolve.common.annotations.ResponseWrapper;
import com.zeeyeh.probsolve.common.annotations.Secret;
import com.zeeyeh.probsolve.common.entity.Result;
import com.zeeyeh.probsolve.common.utils.SecretUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * 统一返回结果处理
 *
 * @author Qinloren
 */
@Getter
@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    private boolean isSecret = false;
    private ResponseWrapper responseWrapper = null;

    @Value("${app.secret.publicKey}")
    private String publicKey;

    @Value("${app.secret.privateKey}")
    private String privateKey;

    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // isSecret = returnType.hasMethodAnnotation(Secret.class);
        // return returnType.hasMethodAnnotation(ResponseWrapper.class);
        Class<?> containingClass = returnType.getContainingClass();
        // 过滤非接口控制器类
        boolean isControllerClass = containingClass.isAnnotationPresent(Controller.class) ||
                containingClass.isAnnotationPresent(RestController.class);
        if (!isControllerClass) {
            return false;
        }
        // 判断方法上是否有 Secret 注解
        boolean methodSecret = returnType.hasMethodAnnotation(Secret.class);
        boolean classSecret = containingClass.isAnnotationPresent(Secret.class);
        boolean methodResponseWrapper = returnType.hasMethodAnnotation(ResponseWrapper.class);
        boolean classResponseWrapper = containingClass.isAnnotationPresent(ResponseWrapper.class);
        responseWrapper = methodResponseWrapper ?
                returnType.getMethodAnnotation(ResponseWrapper.class) :
                containingClass.getAnnotation(ResponseWrapper.class);
        isSecret = methodSecret || classSecret;
        boolean isResponseWrapper = methodResponseWrapper || classResponseWrapper;
        return isResponseWrapper;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object bodyObject, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (bodyObject instanceof Result<?> body) {
            Map<String, Object> headers = body.getHeaders();
            HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
            if (headers != null) {
                headers.forEach((key, value) -> httpServletResponse.setHeader(key, value.toString()));
            }
        }
        return getBody(bodyObject);
    }

    private Object getBody(Object bodyObject) {
        if (responseWrapper == null) {
            return bodyObject;
        }
        JSONObject jsonObject = new JSONObject();
        Object data = null;
        if (bodyObject instanceof Result<?> body) {
            jsonObject.fluentPut("code", body.getCode());
            jsonObject.fluentPut("message", body.getMessage());
            data = body.getData();
        } else {
            jsonObject.fluentPut("code", 0);
            jsonObject.fluentPut("message", "success");
            if (bodyObject instanceof String body) {
                data = body;
            } else {
                data = bodyObject;
            }
        }
        if (responseWrapper.timestamp()) {
            jsonObject.fluentPut("timestamp", System.currentTimeMillis());
        }
        if (isSecret) {
            String encData = null;
            if (data != null) {
                // 生成密钥和初始化向量
                byte[] key = SecretUtil.generateKey();
                byte[] iv = SecretUtil.generateIv();
                // 获取数据的 sign
                String sign = SecretUtil.getSign(data);
                // 将数据和 sign 放入 JSONObject
                JSONObject paramObject = new JSONObject();
                paramObject.put("d", data);
                paramObject.put("s", sign);
                // 将 JSONObject 转换为字符串
                String encryptedData = paramObject.toJSONString();
                // 对字符串进行加密
                String signData = SecretUtil.enc(encryptedData, key, iv);
                // 将密钥、初始化向量和加密数据放入 JSONObject
                JSONObject dataObject = new JSONObject();
                dataObject.put("k", HexUtil.encodeHexStr(key));
                dataObject.put("v", HexUtil.encodeHexStr(iv));
                dataObject.put("d", signData);
                // 将 JSONObject 转换为字符串
                String jsonString = dataObject.toJSONString();
                // 对字符串进行加密
                encData = SecretUtil.encRsa(jsonString, publicKey, privateKey);
            }
            // 将加密数据放入 JSONObject
            jsonObject.fluentPut("data", encData);
        } else {
            jsonObject.fluentPut("data", data);
        }
        return jsonObject;
    }
}

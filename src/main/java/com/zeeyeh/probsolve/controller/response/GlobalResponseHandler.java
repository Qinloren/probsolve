package com.zeeyeh.probsolve.controller.response;

import com.zeeyeh.probsolve.entity.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object bodyObject, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (bodyObject instanceof R<?> body) {
            Map<String, Object> headers = body.getHeaders();
            if (headers != null) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    response.getHeaders().add(entry.getKey(), entry.getValue().toString());
                }
            }
            return body;
        }
        return R.any(0, bodyObject);
    }
}

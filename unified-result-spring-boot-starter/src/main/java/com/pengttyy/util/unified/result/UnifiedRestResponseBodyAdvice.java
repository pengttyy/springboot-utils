package com.pengttyy.util.unified.result;

import com.pengttyy.util.unified.result.annotation.IgnoreRestUnifiedResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一结果处理
 *
 * @author kai.peng
 */
@RestControllerAdvice
public class UnifiedRestResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return (!returnType.getDeclaringClass().isAnnotationPresent(IgnoreRestUnifiedResult.class)) &&
                (!returnType.hasMethodAnnotation(IgnoreRestUnifiedResult.class)) &&
                !StringHttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body) {
            return RestResponse.success();
        } else if (body instanceof RestResponse) {
            return body;
        }
        return RestResponse.success(body);
    }
}

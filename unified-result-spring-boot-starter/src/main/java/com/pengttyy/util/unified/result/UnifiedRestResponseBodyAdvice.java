package com.pengttyy.util.unified.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author kai.peng
 */
@ControllerAdvice
public class UnifiedRestResponseBodyAdvice implements ResponseBodyAdvice {

    private ObjectMapper objectMapper;

    public UnifiedRestResponseBodyAdvice(@Autowired ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.hasMethodAnnotation(UnRestUnifiedResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body) {
            return RestResponse.success();
        } else if (body instanceof RestResponse) {
            return body;
        } else if (body instanceof String) {
            try {
                return this.objectMapper.writeValueAsString(RestResponse.success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return RestResponse.success(body);
    }
}

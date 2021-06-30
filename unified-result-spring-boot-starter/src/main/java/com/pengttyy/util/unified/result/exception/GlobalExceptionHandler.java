/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.pengttyy.util.unified.result.exception;

import com.pengttyy.util.unified.result.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author pengkai
 * @date 2021年06月09日 17:10
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public RestResponse baseException(BaseException e) {
        log.error("业务异常处理！", e);
        return RestResponse.failure(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object systemException(Exception e) {
        log.error("系统异常处理！", e);
        return RestResponse.failure(e.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, RestResponse.failure(ex.getMessage()), headers, status, request);
    }
}
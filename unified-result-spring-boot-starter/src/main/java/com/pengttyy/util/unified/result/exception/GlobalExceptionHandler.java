/*
 *
 *  * frxs Inc.  object.
 *  * Copyright (c) 2017-2022. All Rights Reserved.
 *
 */
package com.pengttyy.util.unified.result.exception;

import com.pengttyy.util.unified.result.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

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
        log.error("http 状态异常", ex);
        String errMsg = "";
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            if (bindingResult.hasErrors()) {
                String validateMessage = bindingResult.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining(System.lineSeparator()));
                return super.handleExceptionInternal(ex, RestResponse.failure(validateMessage), headers, status, request);
            }
        }
        return super.handleExceptionInternal(ex, RestResponse.failure(ex.getMessage()), headers, status, request);
    }
}
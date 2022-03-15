/*
 *
 *  * frxs Inc.  object.
 *  * Copyright (c) 2017-2022. All Rights Reserved.
 *
 */
package com.pengttyy.util.unified.result.exception;

import com.pengttyy.util.unified.result.RestResponse;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * @author pengkai
 * @date 2021年06月11日 16:02
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizedErrorController implements ErrorController {
    private static final String ERROR_INTERNAL_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    public static final RestResponse FAILURE = RestResponse.failure("系统异常！！");
    private static final String NO_ERRORS = "No errors";

    @RequestMapping
    public ResponseEntity<RestResponse> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        WebRequest webRequest = new ServletWebRequest(request);
        Optional<Throwable> error = getError(webRequest);
        String errMsg = error.map(this::getErrorMessage).orElse(NO_ERRORS);
        return ResponseEntity.status(status).body(RestResponse.failure(errMsg));
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public Optional<Throwable> getError(WebRequest webRequest) {
        Throwable error = getAttribute(webRequest, ERROR_INTERNAL_ATTRIBUTE);
        error = error == null ? getAttribute(webRequest, "javax.servlet.error.exception") : error;
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }
        }
        return Optional.ofNullable(error);
    }

    private String getErrorMessage(Throwable error) {
        if (error == null) {
            return NO_ERRORS;
        }
        BindingResult result = extractBindingResult(error);
        if (result == null) {
            return error.getMessage();
        }
        if (result.hasErrors()) {
            return "Validation failed for object='" + result.getObjectName() + "'. Error count: " + result.getErrorCount();
        }
        return NO_ERRORS;
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
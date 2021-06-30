/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.pengttyy.util.unified.result.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author pengkai
 * @date 2021年06月16日 16:10
 */
public class BaseResponseStatus extends ResponseStatusException {
    public BaseResponseStatus(HttpStatus status) {
        super(status);
    }

    public BaseResponseStatus(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BaseResponseStatus(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
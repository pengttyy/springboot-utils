/*
 *
 *  * frxs Inc. object.
 *  * Copyright (c) 2017-2022. All Rights Reserved.
 *
 */
package com.pengttyy.util.unified.result.exception;

/**
 * @author pengkai
 * @date 2021年06月16日 15:16
 */
public class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
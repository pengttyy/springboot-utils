package com.pengttyy.util.unified.result;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 统一返回结果
 *
 * @author kai.peng
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface UnRestUnifiedResult {
}

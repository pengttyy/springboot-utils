package com.pengttyy.util.unified.result.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 忽略统一返回结果
 * 有此注解的方法不对返回对象包装处理
 *
 * @author kai.peng
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
@Documented
public @interface IgnoreRestUnifiedResult {
}

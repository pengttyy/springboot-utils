package com.pengttyy.util.unified.result.annotation;

import com.pengttyy.util.unified.result.autoconfigure.UnifiedResultAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用统一结果处理
 * @author kai.peng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(UnifiedResultAutoConfiguration.class)
public @interface EnableUnifiedResult {
}

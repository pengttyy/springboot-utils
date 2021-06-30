package com.pengttyy.util.unified.result.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 * @author pengt
 */
@Configuration
@ComponentScan(basePackages = "com.pengttyy.util.unified.result")
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class UnifiedResultAutoConfiguration implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Predicate<HttpMessageConverter<?>> converterPredicate = convert -> convert instanceof StringHttpMessageConverter;
        Optional<HttpMessageConverter<?>> stringConvert = converters.stream().filter(converterPredicate).findFirst();
        converters.removeIf(converterPredicate);
        stringConvert.ifPresent(converters::add);
    }
}

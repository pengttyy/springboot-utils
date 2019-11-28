package com.pengttyy.util.unified.result.autoconfigure;

import com.pengttyy.util.unified.result.UnifiedRestResponseBodyAdvice;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 * @author pengt
 */
@Configuration
@ComponentScan(basePackageClasses = UnifiedRestResponseBodyAdvice.class)
public class UnifiedResultAutoConfiguration {

}

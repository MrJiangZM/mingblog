package com.ming.blog.interceptconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor1()).addPathPatterns("/**");
//        registry.addInterceptor(new TestInterceptor2()).addPathPatterns("/anno/**");
//        registry.addInterceptor(new TestInterceptor2()).addPathPatterns("/anno/tes**");
    }
}

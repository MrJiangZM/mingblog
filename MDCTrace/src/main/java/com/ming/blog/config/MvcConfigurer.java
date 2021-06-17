package com.ming.blog.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Filter;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author dengjunwu
 */
@Slf4j
@SpringBootConfiguration
public class MvcConfigurer implements ApplicationContextAware, WebMvcConfigurer {

    //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);            //resolveLazily属性启用是为了推迟文件解析，以在在api中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSizePerFile(20 * 1024 * 1024);
        resolver.setMaxUploadSize(500 * 1024 * 1024);    //上传文件大小 50M 50*1024*1024
        return resolver;
    }

    @Bean
    public Filter CharacterEncodingFilter() {
        return new CharacterEncodingFilter("utf-8", true);
    }

    @Bean
    public Filter logFilter() {
        return new LoggerFilter();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //定向swagger 位置
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.SortField,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect
        );

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converter.setSupportedMediaTypes(Lists.newArrayList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(HttpMethod.OPTIONS.name(), HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name())
                .allowedHeaders(
                        "token",
                        "channel_id",
                        "Keep-Alive",
                        "User-Agent",
                        "X-Requested-With",
                        "If-Modified-Since",
                        HttpHeaders.CACHE_CONTROL,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.REFERER,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                .allowCredentials(true)
                .maxAge(3600);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(login)
////                .excludePathPatterns("/user/login");
//        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
//    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
    }
}

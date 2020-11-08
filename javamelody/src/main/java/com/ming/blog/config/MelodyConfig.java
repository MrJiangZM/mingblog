package com.ming.blog.config;

import lombok.extern.slf4j.Slf4j;
import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author Jiang Zaiming
 * @date 2019/12/6 9:47 上午
 */
@Slf4j
@Configuration
public class MelodyConfig {

    /**
     * 配置javamelody监控
     * spring boot 会按照order值的大小，从小到大的顺序来依次过滤
     */
    @Bean
    @Order(Integer.MAX_VALUE - 1)
    public FilterRegistrationBean<MonitoringFilter> monitoringFilter() {
        FilterRegistrationBean<MonitoringFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MonitoringFilter());
        registration.addUrlPatterns("/*");
        registration.setName("monitoring");
        return registration;
    }

    /**
     * 配置javamelody监听器sessionListener
     */
    @Bean
    public ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SessionListener> slrBean = new ServletListenerRegistrationBean<>();
        slrBean.setListener(new SessionListener());
        return slrBean;
    }

}

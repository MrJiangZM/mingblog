package com.ming.blog.filterconfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filter1Register() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new TestFilter1());
        bean.addUrlPatterns("/anno/*");
        bean.setName("testFilter1");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filter2Register() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new TestFilter2());
        bean.addUrlPatterns("/anno/test/*");
        bean.setName("testFilter2");
        bean.setOrder(2);
        return bean;
    }


    @Bean
    public FilterRegistrationBean filter3Register() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new TestFilter3());
        bean.addUrlPatterns("/*");
        bean.setName("testFilter3");
        bean.setOrder(3);
        return bean;
    }
}

package com.ming.blog.config;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * quartz 配置相关
 */
@Configuration
public class SchedulerConfig {
	
	@Autowired
	private SpringJobFactory springJobFactory;

    /**
     *
     * @return
     * @throws IOException
     */
    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setAutoStartup(true);
        factory.setStartupDelay(5);//延时5秒启动
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(springJobFactory);  
        return factory;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     * 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
     */
    @Bean
    public QuartzInitializerListener executorListener() {
       return new QuartzInitializerListener();
    }

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name="Scheduler")
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

}
package com...config;

import com.spcode.remote.vo.App;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * quartz 配置相关
 */
@Slf4j
@Configuration
public class SchedulerConfig {

    @Autowired
    private SpringJobFactory springJobFactory;
    @Autowired
    private ApplicationContext context;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int QUEUE_CAPACITY = 999;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final String THREAD_PREFIX_NAME = "async-executor-";
    private static final String THREAD_PREFIX_NAME_TWO = "async-alert-";

//    @Bean(name = "quartzTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
//        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(QUEUE_CAPACITY);
//        executor.setThreadNamePrefix(threadPrefixName);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


    /**
     * @return
     *
     * @throws IOException
     */
    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setTaskExecutor(executor);
        factory.setAutoStartup(true);
        factory.setWaitForJobsToCompleteOnShutdown(true);
        factory.setStartupDelay(5);//延时5秒启动
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(springJobFactory);
        return factory;
    }

    /**
     * @return
     *
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Environment environment = context.getEnvironment();
        propertiesFactoryBean.setLocation(
                new ClassPathResource("/quartz-" + environment.getActiveProfiles()[0] + ".properties"));
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
    @Bean(name = "Scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        // 创建并注册一个全局的Job Listener
        scheduler.getListenerManager().addJobListener(new AllJobListener(), EverythingMatcher.allJobs());
        // 部分的任务监听器
//        scheduler.getListenerManager().addJobListener(new SimpleJobListener(), OrMatcher.or(GroupMatcher.jobGroupEquals("one"), GroupMatcher.jobGroupEquals("two")));
        // 创建并注册一个指定任务的Job Listener
//        scheduler.getListenerManager().addJobListener(new SimpleJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("one", "OneJob")));

        // 创建并注册一个全局的Trigger Listener
        scheduler.getListenerManager().addTriggerListener(new AllTriggerListener(), EverythingMatcher.allTriggers());
        // 创建并注册一个局部的Trigger Listener
//        scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener("SimpleTrigger"), KeyMatcher.keyEquals(TriggerKey.triggerKey("HelloWord1_Job", "HelloWorld1_Group")));
        // 创建并注册一个特定组的Trigger Listener
//        scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener("SimpleTrigger"), GroupMatcher.groupEquals("HelloWorld1_Group"));

        scheduler.getListenerManager().addSchedulerListener(new QuartzSchedulerListener());

        return scheduler;
    }

    /**
     * 暂时注释
     */
//    @PreDestroy
//    public static void shutdown() throws SchedulerException {
//        Scheduler scheduler = (Scheduler) SpringUtil.getBean("Scheduler");
//        scheduler.shutdown(true);
//    }

}
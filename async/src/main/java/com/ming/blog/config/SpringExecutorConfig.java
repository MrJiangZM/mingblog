package com.ming.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Order(1)
public class SpringExecutorConfig {

    private static final int CORE_POOL_SIZE = 5;  // CPU核心数+1
    private static final int MAX_POOL_SIZE = Integer.MAX_VALUE;
    private static final int QUEUE_CAPACITY = 999;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final String THREAD_PREFIX_NAME = "async-executor-";
    private static final String THREAD_PREFIX_NAME_TWO = "async-alert-";

    @Bean(name = "serviceTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);             //配置核心线程数
//        executor.setMaxPoolSize(maxPoolSize);               //配置最大线程数
        executor.setQueueCapacity(QUEUE_CAPACITY);           //配置队列大小
//        executor.setThreadNamePrefix(threadPrefixName);     //配置线程池中的线程的名称前缀
        /**
         * rejection-policy：当pool已经达到max size的时候，如何处理新任务
         * CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
         */
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.initialize();        //初始化执行器
        return executor;
    }

    //    @Bean(name = "alertToNanjingExecutor")
    public Executor alertToNanjingExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);        //配置核心线程数
//        executor.setMaxPoolSize(maxPoolSize);        //配置最大线程数 ， 使用默认integer.max
//        executor.setQueueCapacity(queueCapacity);        //配置队列大小
        executor.setThreadNamePrefix(THREAD_PREFIX_NAME_TWO);        //配置线程池中的线程的名称前缀
        /**
         * rejection-policy：当pool已经达到max size的时候，如何处理新任务
         * CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();        //初始化执行器
        return executor;
    }

}

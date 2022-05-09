package com.ming.blog.three;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class MedCorpTaskExecutorConfig {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 50;
    private static final int QUEUE_CAPACITY = 5000;
    private static final int KEEP_ALIVE_SECONDS = 300;
    private static final String THREAD_PREFIX_NAME = "async-executor-";

    /**
     * rejection-policy：当pool已经达到max size的时候，如何处理新任务
     * CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
     */
    @Bean(name = "serviceTaskExecutorOne")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setTaskDecorator(new TraceTaskDecorator());
        executor.setAwaitTerminationSeconds(60);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_PREFIX_NAME);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}

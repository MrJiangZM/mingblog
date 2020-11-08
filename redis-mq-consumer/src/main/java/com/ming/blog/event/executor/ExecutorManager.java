package com.ming.blog.event.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiangzaiming
 */
@Component
public class ExecutorManager {

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    public void executorWorker(BaseDaemonWorker worker) {
        taskExecutor.execute(worker);
    }

    public void executorMethod(Runnable thread) {
        taskExecutor.execute(thread);
    }
}

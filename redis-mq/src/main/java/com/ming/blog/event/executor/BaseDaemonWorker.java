package com.ming.blog.event.executor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author jiangzaiming
 */
public abstract class BaseDaemonWorker extends BaseTimeWorker implements Runnable {

    @Resource
    private ExecutorManager executorManager;

    @PostConstruct
    public void startExecute() {
        executorManager.executorWorker(this);
    }

    protected void executeMethod(Runnable thread) {
        executorManager.executorMethod(thread);
    }
}

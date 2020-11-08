package com.ming.blog.mq.event.executor;

/**
 * @author jiangzaiming
 */
public abstract class BaseTimeWorker {

    protected void actualWork() {
        process();
    }

    protected abstract void process();

}

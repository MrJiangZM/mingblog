package com.ming.blog.event.executor;

/**
 * @author jiangzaiming
 */
public abstract class BaseTimeWorker {

    protected void actualWork() {
        process();
    }

    protected abstract void process();

}

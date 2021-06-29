package com.ming.blog.executor;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class TaskUtil<V> {

    @Resource
    private Executor serviceTaskExecutorOne;

    /**
     * 有返回结果 使用completable
     *
     * @return CompletableFuture<V>
     */
    public CompletableFuture<V> submitCompletableValue(ServiceFutureTask<V> serviceFutureTask) {
        return CompletableFuture.supplyAsync(serviceFutureTask :: task, serviceTaskExecutorOne);
    }

    /**
     * 没有返回结果 使用completable
     *
     * @return CompletableFuture<Void>
     */
    public CompletableFuture<Void> submitCompletableVoid(ServiceVoidTask serviceVoidTask) {
        return CompletableFuture.runAsync(serviceVoidTask :: task, serviceTaskExecutorOne);
    }


}

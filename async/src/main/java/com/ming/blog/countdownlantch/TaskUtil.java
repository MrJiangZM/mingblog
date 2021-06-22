package com.ming.blog.countdownlantch;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/21</pre>
 */
@Component
public class TaskUtil<V> {

    @Resource
    private Executor serviceTaskExecutor1;

    @Resource
    private ThreadPoolTaskExecutor serviceTaskExecutor2;

    @Resource
    private Executor serviceTaskExecutor3;

    /**
     * 没有返回结果
     *
     * @param serviceVoidTask serviceTask
     * @param countDownLatch  countDownLatch
     */
    public void executeVoid(ServiceVoidTask serviceVoidTask, CountDownLatch countDownLatch) {
        serviceTaskExecutor1.execute(() -> {
            try {
                // 可以对线程进行处理，比如添加traceId 调用信息等，配合分布式项目监控需要的内容
                serviceVoidTask.task();
            } finally {
                countDownLatch.countDown();
            }
        });
    }

    /**
     * 有返回结果
     *
     * @param serviceFutureTask serviceFutureTask
     *
     * @return ListenableFuture<V>
     */
    public ListenableFuture<V> submit(ServiceFutureTask serviceFutureTask) {
        Long traceId = 111111111111111L;
        System.out.println(Thread.currentThread().getName() + "-thread name with traceId-" + traceId);
        ListenableFutureTask<V> future = new ListenableFutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "-thread name with traceId-" + traceId);
            return serviceFutureTask.task();
        });
        serviceTaskExecutor2.submit(future);
        return future;
    }

    /**
     * 有返回结果 使用completable
     *
     * @return
     */
    public CompletableFuture<V> submitCompletableValue(ServiceFutureTask<V> serviceFutureTask) {
        Long traceId = 222222222222222222L;
        System.out.println(Thread.currentThread().getName() + "-sub thread name with traceId-" + traceId);
        return CompletableFuture.supplyAsync(() -> {
            // 可以对线程进行处理，比如添加traceId 调用信息等，配合分布式项目监控需要的内容
            System.out.println(Thread.currentThread().getName() + "-thread name with traceId-" + traceId);
            return serviceFutureTask.task();
        }, serviceTaskExecutor1);
    }

    /**
     * 没有返回结果 使用completable
     *
     * @return
     */
    public CompletableFuture<Void> submitCompletableVoid(ServiceVoidTask serviceVoidTask) {
        Long traceId = 222222222222222222L;
        System.out.println(Thread.currentThread().getName() + "-sub thread name with traceId-" + traceId);
        return CompletableFuture.runAsync(() -> {
            // 可以对线程进行处理，比如添加traceId 调用信息等，配合分布式项目监控需要的内容
            System.out.println(Thread.currentThread().getName() + "-thread name with traceId-" + traceId);
            serviceVoidTask.task();
        }, serviceTaskExecutor1);
    }

}

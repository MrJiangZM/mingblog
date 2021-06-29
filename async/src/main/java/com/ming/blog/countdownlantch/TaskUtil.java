package com.ming.blog.countdownlantch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
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
@Slf4j
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

    /**
     * 有返回结果 使用completable
     *
     * @return
     */
    public ListenableFuture<V> submitFutureTaskWithListener(ServiceFutureTask<V> serviceFutureTask,
                                                            ListenableFutureCallback<V> listenableFutureCallback) {
        Long traceId = 222222222222222222L;
        System.out.println(Thread.currentThread().getName() + "-sub thread name with traceId-" + traceId);
        ListenableFuture<V> vListenableFuture = serviceTaskExecutor2.submitListenable(() -> {
            System.out.println(Thread.currentThread().getName() + "-thread name with traceId-" + traceId);
            return serviceFutureTask.task();
        });
        vListenableFuture.addCallback(listenableFutureCallback);
        return vListenableFuture;
    }


    public void testAsync() {

        ListenableFuture<TestCountDownLatchController.UserTest> task = serviceTaskExecutor2.submitListenable(() -> {
            TestCountDownLatchController.UserTest test = new TestCountDownLatchController.UserTest();
            test.setAge(100);
            test.setId(1);
            test.setName("大明");
            return test;
        });

        task.addCallback(new ListenableFutureCallback<TestCountDownLatchController.UserTest>() {

            @Override
            public void onSuccess(TestCountDownLatchController.UserTest userTest) {
                if (userTest == null) {
                    userTest = new TestCountDownLatchController.UserTest(2, "小明", 500);
                }
                // 需要做的任务
                // doSomething
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("Error executing callback.{}", t);
            }
        });
    }


}

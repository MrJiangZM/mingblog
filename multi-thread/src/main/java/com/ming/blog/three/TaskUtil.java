package com.ming.blog.three;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * 添加注释使用
 */
@Component
public class TaskUtil {

    @Resource
    private Executor serviceTaskExecutorOne;

    /**
     * 有返回结果 使用completable
     *
     * @return CompletableFuture<V>
     */
    public <V> CompletableFuture<V> submitCompletableValue(ServiceFutureTask<V> serviceFutureTask) {
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

    /**
     * 没有返回结果，带CountDownLatch
     * 注释部分可以用来携带trace等信息
     *
     * @return CompletableFuture<Void>
     */
    public void submitTaskWithCountDownLatch(CountDownLatch countDownLatch, ServiceVoidTask serviceVoidTask) {
//        String traceId = TraceHelper.getUniqueKey();
//        String traceLevel = TraceHelper.getLevel();
//        String version = VersionData.getVersion();
        serviceTaskExecutorOne.execute(() -> {
            try {
//                VersionData.setVersion(version);
//                TraceHelper.init(traceId, traceLevel + "." + RandomUtils.randomInt(10000, 99999), null);
                serviceVoidTask.task();
            } finally {
                countDownLatch.countDown();
            }
        });
    }


}

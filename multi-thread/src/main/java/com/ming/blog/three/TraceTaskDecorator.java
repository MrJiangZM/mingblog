package com.ming.blog.three;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;

@Slf4j
public class TraceTaskDecorator implements TaskDecorator {

    /**
     * 注释部分可以用来传递trace等
     * @param runnable
     * @return
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        long start = System.currentTimeMillis();
        log.info("threadName:{}, {}", Thread.currentThread().getName(),  start);
//        String traceId = TraceHelper.getUniqueKey();
//        String traceLevel = TraceHelper.getLevel();
//        String version = VersionData.getVersion();
//        log.info("threadName:{}, traceId:{}, traceLevel:{}, version:{}", Thread.currentThread().getName(), traceId, traceLevel, version);
        return () -> {
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            try {
//                VersionData.setVersion(version);
//                TraceHelper.init(traceId, traceLevel + "." + RandomUtils.randomInt(10000, 50000), null);
//                log.info("threadName:{}, time:{}, traceId:{}, traceLevel:{}, version:{}", Thread.currentThread().getName(), System.currentTimeMillis() - start, TraceHelper.getUniqueKey(), TraceHelper.getLevel(), TraceHelper.getNextOrder());
                runnable.run();
                log.info("threadName:{}, {}", Thread.currentThread().getName(), System.currentTimeMillis() - start);
            } finally {
                // TraceHelper 没有提供remove方法
                threadLocal.remove();
            }
        };
    }
}

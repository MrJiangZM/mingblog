package com.ming.blog.executor;

import org.springframework.core.task.TaskDecorator;

public class TraceTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        // 添加traceId等信息
        return () -> {
            ThreadLocal<Object> threadLocal = new ThreadLocal<>();
            try {
                runnable.run();
            } finally {
                threadLocal.remove();
            }
        };
    }
}

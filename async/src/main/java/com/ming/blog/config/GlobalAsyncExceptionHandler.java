package com.ming.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Jiang Zaiming
 * @date 2020/5/29 5:25 下午
 */
@Component
public class GlobalAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error("[handleUncaughtException][method({}) params({}) 发生异常]",
                method, params, ex);
    }

}

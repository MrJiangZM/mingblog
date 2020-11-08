package com.ming.blog.disruptor;

import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:16 下午
 */
@Slf4j
public class NotifyEventHandlerException implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable throwable, long l, Object o) {
        throwable.fillInStackTrace();
        log.error("process data error sequence ==[{}] event==[{}] ,ex ==[{}]", l, o.toString(), throwable.getMessage());
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        log.error("start disruptor error ==[{}]!", throwable.getMessage());
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        log.error("shutdown disruptor error ==[{}]!", throwable.getMessage());
    }

}

package com.ming.blog.disruptor;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:05 下午
 */
@Slf4j
public class NotifyEventHandler implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
        log.info("接收到了消息{}, {}, {}", notifyEvent, l, b);
        this.onEvent(notifyEvent);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        Thread.sleep(3000L);
        log.info(" 处理==业务逻辑 ==>> {}", notifyEvent);
    }

}

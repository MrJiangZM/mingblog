package com.ming.blog.disruptor;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:05 下午
 */
@Slf4j
public class NotifyEventHandlerFour implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
//        log.info("接收到了消息4444444444444444{}, {}, {}", notifyEvent, l, b);
//        this.onEvent(notifyEvent);
        System.out.println("处理==业务逻辑444444444444 ==>>   EventHandler" + l);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        Thread.sleep(1000L);
//        Thread.sleep(3000L);
//        System.out.println("处理==业务逻辑444444444444 ==>>   WorkHandler ");
    }

}

package com.ming.blog.disruptor;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:05 下午
 */
@Slf4j
public class NotifyEventHandlerThree implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
//        log.info("接收到了消息33333333333333{}, {}, {}", notifyEvent, l, b);
//        this.onEvent(notifyEvent);
        System.out.println("处理==业务逻辑3333333333333 ==>>    EventHandler" + l);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        Thread.sleep(100L);
//        Thread.sleep(3000L);
        System.out.println("处理==业务逻辑3333333333333 ==>>    WorkHandler");
    }

}

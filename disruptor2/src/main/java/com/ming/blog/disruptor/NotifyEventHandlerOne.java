package com.ming.blog.disruptor;


import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:05 下午
 */
@Slf4j
public class NotifyEventHandlerOne implements EventHandler<NotifyEvent>, WorkHandler<NotifyEvent> {

    @Override
    public void onEvent(NotifyEvent notifyEvent, long l, boolean b) throws Exception {
//        log.info("接收到了消息111111111111{}, {}, {}", notifyEvent, l, b);
//        this.onEvent(notifyEvent);
        System.out.println("处理==业务逻辑11111111111 ==>>   EventHandler" + l);
    }

    @Override
    public void onEvent(NotifyEvent notifyEvent) throws Exception {
        Thread.sleep(100L);
//        Thread.sleep(1000L);
//        log.info(" 处理==业务逻辑11111111111 ==>> {}", JSONObject.toJSONString(String.valueOf(notifyEvent.getMessage())));
//        log.info(" 处理==业务逻辑11111111111 ==>> {}", notifyEvent);
        System.out.println("处理==业务逻辑11111111111 ==>>    WorkHandler");
    }

}

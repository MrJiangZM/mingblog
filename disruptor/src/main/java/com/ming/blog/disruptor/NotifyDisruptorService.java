package com.ming.blog.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:18 下午
 */
@Slf4j
@Service
public class NotifyDisruptorService implements DisposableBean, InitializingBean {

    private Disruptor<NotifyEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;


    @Override
    public void destroy() throws Exception {
        log.info("shutdown disruptor");
        disruptor.shutdown();
    }

    /*
     * createSingleProducer创建一个单生产者的RingBuffer，
     * 第一个参数叫EventFactory，从名字上理解就是“事件工厂”，其实它的职责就是产生数据填充RingBuffer的区块。
     * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
     * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        disruptor = new Disruptor<NotifyEvent>(new NotifyEventFactory(), RING_BUFFER_SIZE,
                Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());
        disruptor.handleEventsWith(new NotifyEventHandler());
        disruptor.start();
    }

    public void sendNotify(String message) {
        RingBuffer<NotifyEvent> ringBuffer = disruptor.getRingBuffer();
//        ringBuffer.publishEvent(new EventTranslatorOneArg<NotifyEvent,  String>() {
//            @Override
//            public void translateTo(NotifyEvent event, long sequence, String data) {
//                event.setMessage(data);
//            }
//        }, message);
        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), message); //lambda式写法，如果是用jdk1.8以下版本使用以上注释的一段

    }


}

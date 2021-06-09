package com.ming.blog.controller;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.ming.blog.disruptor.*;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:00 下午
 */
public class TestService {

    public static void main(String[] args) {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();
        NotifyEventFactory eventFactory = new NotifyEventFactory();
        int bufferSize = 8;

        //ProducerType.MULTI 暂未测试这种情况
        Disruptor<NotifyEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, producerFactory,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        NotifyEventHandlerOne one = new NotifyEventHandlerOne();
        NotifyEventHandlerTwo two = new NotifyEventHandlerTwo();
        NotifyEventHandlerThree three = new NotifyEventHandlerThree();
        NotifyEventHandlerFour four = new NotifyEventHandlerFour();
        NotifyEventHandlerFive five = new NotifyEventHandlerFive();

//        结合缓冲区，当前缓冲区满了才会走下一步
//        1,2,last顺序执行
//        disruptor.handleEventsWith(one).handleEventsWith(two)
//                .handleEventsWith(five);

//        也是1，2，last顺序执行
//        disruptor.handleEventsWith(one);
//        disruptor.after(one).handleEventsWith(two).then(five);

        //1,2并发执行，之后才是last
//        disruptor.handleEventsWith(one, two);
//        disruptor.after(one, two).handleEventsWith(five);

        //1后2，3后4，1和3并发，2和4都结束后last
        disruptor.handleEventsWith(one, three);
        disruptor.after(one).handleEventsWith(two);
        disruptor.after(three).handleEventsWith(four);
        disruptor.after(two, four).handleEventsWith(five);

//        1 2 全部执行接收到的消息
//        disruptor.handleEventsWith(one, two);

//        这个是类似于队列，公屏消费，消费总量等于消息总量
//        disruptor.handleEventsWithWorkerPool(one, two);

        disruptor.start();

        RingBuffer<NotifyEvent> ringBuffer = disruptor.getRingBuffer();
        EventProducer eventProducer = new EventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(16);
        eventProducer.onData(bb);

        // 缓冲区的使用 ！！！！！ 可以做队列来使用
//        for (long i = 0; i < 10L; i++) {
//            bb.putLong(0, i);
//            eventProducer.onData(bb);
//        }
        disruptor.shutdown();
    }

}

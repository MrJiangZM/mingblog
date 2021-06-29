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
public class SingleService {

    public static void main(String[] args) {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();
        NotifyEventFactory eventFactory = new NotifyEventFactory();
        int bufferSize = 4;

        //ProducerType.MULTI 暂未测试这种情况
        Disruptor<TestEvent> disruptor = new Disruptor<TestEvent>(eventFactory, bufferSize, producerFactory,
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());



        RingBuffer<TestEvent> ringBuffer = disruptor.getRingBuffer();

        TestEventHandler one = new TestEventHandler("小明", ringBuffer);
        TestEventHandler two = new TestEventHandler("小红", ringBuffer);
        TestEventHandler three = new TestEventHandler("小刚", ringBuffer);
        TestEventHandler four = new TestEventHandler("小李", ringBuffer);
        TestEventHandler five = new TestEventHandler("小马", ringBuffer);

//        1 2 全部执行接收到的消息   类似于 发布订阅模式
//        disruptor.handleEventsWith(one, two);

//        这个是类似于队列，公屏消费，消费总量等于消息总量
//        两者不同在于前者只有一个消费者执行 下面的所有消费者执行
        disruptor.handleEventsWith(four, five);

//        所有消费着都执行
//        disruptor.handleEventsWith(one, three, two, four, five);

//        结合缓冲区，当前缓冲区满了才会走下一步
//        1,2,last顺序执行
//        disruptor.handleEventsWith(one).handleEventsWith(two).thenHandleEventsWithWorkerPool(three, four)
//                .handleEventsWith(five);

//        也是1，2，last顺序执行
//        disruptor.handleEventsWith(one);
//        以下两个等价
//        disruptor.after(one).handleEventsWith(two).then(five);
//        disruptor.after(one).then(two).then(five);


        //1,2并发执行，之后才是last
//        disruptor.handleEventsWith(one, two).handleEventsWith(five);

//        disruptor.handleEventsWith(one, two);
//        disruptor.after(one, two).handleEventsWith(five);

        //1后2，3后4，1和3并发，2和4都结束后last
//        disruptor.handleEventsWithWorkerPool(one, three).thenHandleEventsWithWorkerPool(two, four).handleEventsWith(five);
//      基本同上 但是每个消费者都会执行
//        disruptor.handleEventsWith(one, three);
//        disruptor.after(one).handleEventsWith(two);
//        disruptor.after(three).handleEventsWith(four);
//        disruptor.after(two, four).handleEventsWith(five);

        disruptor.start();

        EventProducer eventProducer = new EventProducer(ringBuffer, 1);
        for (int i = 0; i < 10L; i++) {
            eventProducer.sendDataEventHandler(i);
        }

//         缓冲区的使用 ！！！！！ ByteBuffer 可以做队列来使用
//        for (long i = 0; i < 10L; i++) {
//            bb.putLong(0, i);
//            eventProducer.onData(bb);
//        }
        disruptor.shutdown();
    }

}

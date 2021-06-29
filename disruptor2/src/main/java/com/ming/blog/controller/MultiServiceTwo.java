package com.ming.blog.controller;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.ming.blog.disruptor.EventProducer;
import com.ming.blog.disruptor.NotifyEventFactory;
import com.ming.blog.disruptor.NotifyEventHandlerException;
import com.ming.blog.disruptor.TestEvent;
import com.ming.blog.disruptor.TestEventHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:00 下午
 */
public class MultiServiceTwo {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();
        NotifyEventFactory eventFactory = new NotifyEventFactory();
        int bufferSize = 256;

        Disruptor<TestEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, producerFactory,
                ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());
        RingBuffer<TestEvent> ringBuffer = disruptor.getRingBuffer();

        TestEventHandler one = new TestEventHandler("小明", ringBuffer);
        TestEventHandler two = new TestEventHandler("小红", ringBuffer);
        TestEventHandler three = new TestEventHandler("小刚", ringBuffer);
        TestEventHandler four = new TestEventHandler("小李", ringBuffer);
        TestEventHandler five = new TestEventHandler("小马", ringBuffer);

        disruptor.handleEventsWithWorkerPool(one, three, two, four, five);

        disruptor.start();

        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 2; i++) {
            final EventProducer producer = new EventProducer(ringBuffer, i);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 50; j++) {
                        producer.sendDataForMulti(j * 10);
                    }
                }
            }).start();
        }

        System.err.println("----------线程创建完毕，开始生产数据----------");
        latch.countDown();
        System.out.println("等待任务执行");
        Thread.sleep(10000);

        disruptor.shutdown();
        System.out.println("任务执行成功 shutdown");
    }

}

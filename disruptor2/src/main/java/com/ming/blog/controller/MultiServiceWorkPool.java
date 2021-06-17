package com.ming.blog.controller;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.ming.blog.disruptor.EventProducer;
import com.ming.blog.disruptor.NotifyEvent;
import com.ming.blog.disruptor.NotifyEventFactory;
import com.ming.blog.disruptor.NotifyEventHandlerException;
import com.ming.blog.disruptor.NotifyEventHandlerFive;
import com.ming.blog.disruptor.NotifyEventHandlerFour;
import com.ming.blog.disruptor.NotifyEventHandlerOne;
import com.ming.blog.disruptor.NotifyEventHandlerThree;
import com.ming.blog.disruptor.NotifyEventHandlerTwo;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:00 下午
 */
public class MultiServiceWorkPool {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory producerFactory = Executors.defaultThreadFactory();
        NotifyEventFactory eventFactory = new NotifyEventFactory();
        int bufferSize = 256;

        //ProducerType.MULTI 暂未测试这种情况
        Disruptor<NotifyEvent> disruptor = new Disruptor<NotifyEvent>(eventFactory, bufferSize, producerFactory,
                ProducerType.MULTI, new BlockingWaitStrategy());
//        disruptor.setDefaultExceptionHandler(new NotifyEventHandlerException());

        NotifyEventHandlerOne one = new NotifyEventHandlerOne();
        NotifyEventHandlerTwo two = new NotifyEventHandlerTwo();
        NotifyEventHandlerThree three = new NotifyEventHandlerThree();
        NotifyEventHandlerFour four = new NotifyEventHandlerFour();
        NotifyEventHandlerFive five = new NotifyEventHandlerFive();

        RingBuffer<NotifyEvent> ringBuffer = disruptor.getRingBuffer();

        //2 通过Ringbuffer创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        WorkHandler[] workHandlers = {one, three, two, four, five};

        //4 构建多消费者工作池
        WorkerPool<NotifyEvent> workerPool = new WorkerPool<NotifyEvent>(
                ringBuffer,
                sequenceBarrier,
                new NotifyEventHandlerException(),
                workHandlers);

        //5 设置多个消费者的sequence序号 用于单独统计消费进度, 并且设置到ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        //6 启动
        ExecutorService es = Executors.newFixedThreadPool(5);
        workerPool.start(es);

        final CountDownLatch latch = new CountDownLatch(1);
//        CyclicBarrier barrier = new CyclicBarrier(100);

        for (int i = 0; i < 10; i++) {
            final EventProducer producer = new EventProducer(ringBuffer, i);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
//                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 5; j++) {
                        producer.sendDataForMulti(j * 10);
                    }
                }
            }).start();
        }

        System.err.println("----------线程创建完毕，开始生产数据----------");
        latch.countDown();
        System.out.println("任务执行成功");
        Thread.sleep(10000);
        System.out.println("任务执行成功");

//        disruptor.start();
        disruptor.shutdown();
        es.shutdown();
        System.out.println("任务执行成功 shutdown");
    }

}

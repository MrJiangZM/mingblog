package com.ming.blog.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:25 下午
 */
public class EventProducer {

    private final RingBuffer<TestEvent> ringBuffer;
    private Integer id;

    public EventProducer(RingBuffer<TestEvent> ringBuffer, Integer id) {
        this.ringBuffer = ringBuffer;
        this.id = id;
    }

    public void sendDataEventHandler(Integer id) {
        long sequence = ringBuffer.next();
        try {
            TestEvent testEvent = ringBuffer.get(sequence);
            testEvent.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
//        long sequence = ringBuffer.next();
        // Grab the next sequence
//        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), bb);
//        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), bb);
    }

    public void sendDataForMulti(Integer id) {
        long sequence = ringBuffer.next();
        try {
            System.out.println("生产者" + id + ", 线程--" + Thread.currentThread().getName() + ", 在往sequence上添加数据" + sequence);
            TestEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}

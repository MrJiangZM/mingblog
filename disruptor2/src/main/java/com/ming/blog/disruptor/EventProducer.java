package com.ming.blog.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:25 下午
 */
public class EventProducer {

    private final RingBuffer<NotifyEvent> ringBuffer;
    private Integer id;

    public EventProducer(RingBuffer<NotifyEvent> ringBuffer, Integer id) {
        this.ringBuffer = ringBuffer;
        this.id = id;
    }

    public void sendDataEventHandler(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            NotifyEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setMessage(bb);
        } finally {
            ringBuffer.publish(sequence);
        }
//        long sequence = ringBuffer.next();
        // Grab the next sequence
//        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), bb);
//        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), bb);
    }

    public void sendDataForMulti(Integer uuid) {
        long sequence = ringBuffer.next();
        try {
            System.out.println("生产者" + id + ", 线程--" + Thread.currentThread().getName() + ", 在往sequence上添加数据" + sequence);
            NotifyEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setId(uuid);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}

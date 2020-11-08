package com.ming.blog.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 6:25 下午
 */
public class EventProducer {

    private final RingBuffer<NotifyEvent> ringBuffer;

    public EventProducer(RingBuffer<NotifyEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
//        long sequence = ringBuffer.next();
        // Grab the next sequence
        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), bb);
    }
}

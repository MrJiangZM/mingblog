package com.ming.blog.disruptor;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.awt.image.ImageConsumer;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:05 下午
 */
@Slf4j
@AllArgsConstructor
public class TestEventHandler implements EventHandler<TestEvent>, WorkHandler<TestEvent> {

    private String name;
    private RingBuffer<TestEvent> ringBuffer;

    @Override
    public void onEvent(TestEvent testEvent, long l, boolean b) throws Exception {
        System.out.println("EventHandler--" + name + ", sequence-" + l);
    }

    @Override
    public void onEvent(TestEvent testEvent) throws Exception {
//        Thread.sleep(100L);
        System.out.println("WorkHandler--" + name + ", cursor-" + ringBuffer.getCursor());
    }

}

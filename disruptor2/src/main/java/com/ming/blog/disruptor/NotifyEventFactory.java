package com.ming.blog.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 消息载体，消息的工厂，在ringBuffer中占据一种一个区块
 *
 * @author Jiang Zaiming
 * @date 2020/6/5 4:08 下午
 */
public class NotifyEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new TestEvent();
    }
}

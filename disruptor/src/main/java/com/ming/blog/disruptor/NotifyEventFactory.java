package com.ming.blog.disruptor;

import com.lmax.disruptor.EventFactory;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:08 下午
 */
public class NotifyEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new NotifyEvent();
    }
}

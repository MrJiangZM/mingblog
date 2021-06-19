package com.ming.blog.order;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.ProducerType;
import com.ming.blog.config.BaseQueueHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadFactory;

@Component
public class OrderDataEventQueueHelper extends BaseQueueHelper<OrderData, OrderDataEvent, OrderDataEventHandler> implements InitializingBean {

    private static final int QUEUE_SIZE = 1024;

    @Resource
    private List<OrderDataEventHandler> orderDataEventHandlers;


    @Override
    protected int getQueueSize() {
        return QUEUE_SIZE;
    }

    @Override
    protected EventFactory eventFactory() {
        return new OrderEventFactory();
    }

    @Override
    protected WorkHandler[] getWorkHandler() {
        int size = orderDataEventHandlers.size();
        OrderDataEventHandler[] paramEventHandlers = (OrderDataEventHandler[]) orderDataEventHandlers.toArray(new OrderDataEventHandler[size]);
        return paramEventHandlers;
    }

    @Override
    protected EventHandler[] getEventHandler() {
        return null;
    }

    @Override
    protected ProducerType getProducerType() {
        return ProducerType.SINGLE;
    }

    @Override
    protected void publishConfig() {
        disruptor.handleEventsWithWorkerPool(getWorkHandler());
    }

    @Override
    protected WaitStrategy getStrategy() {
        return new BlockingWaitStrategy();
        //return new YieldingWaitStrategy();
    }

    @Override
    protected ThreadFactory getThreadFactory() {
        return new ThreadFactoryBuilder().setNameFormat("DisruptorOrder").build();
    }

    @Override
    public void afterPropertiesSet() {
        this.addInitQueue();
        this.init();
    }

    /**
     * 可以初始化队列中的元素
     */
    public void addInitQueue() {
//        initQueue.add(new OrderData(123L, "小马的", BigDecimal.TEN));
    }

}
package com.ming.blog.config;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public abstract class BaseQueueHelper<D, E extends ValueWrapper<D>, H extends WorkHandler<E>> {

    private static final List<BaseQueueHelper> queueHelperList = new ArrayList<>();
    /**
     * Disruptor 对象
     */
    protected Disruptor<E> disruptor;
    /**
     * RingBuffer
     */
    private RingBuffer<E> ringBuffer;
    /**
     * initQueue
     */
    protected List<D> initQueue = new ArrayList<>();

    /**
     * 队列大小
     *
     * @return 队列长度，必须是2的幂
     */
    protected abstract int getQueueSize();

    /**
     * 事件工厂
     *
     * @return EventFactory
     */
    protected abstract EventFactory<E> eventFactory();

    /**
     * 事件消费者
     *
     * @return WorkHandler[]  点对点模式 消费总量等于消息总量
     */
    protected abstract WorkHandler[] getWorkHandler();

    /**
     * 事件消费者
     *
     * @return WorkHandler[]  点对点模式 消费总量等于消息总量
     */
    protected abstract EventHandler[] getEventHandler();

    /**
     * 事件消费者
     *
     * @return WorkHandler[]  点对点模式 消费总量等于消息总量
     */
    protected abstract ProducerType getProducerType();


    /**
     * 队列类型
     *
     * @return WorkHandler[]  点对点模式 消费总量等于消息总量
     */
    protected abstract void publishConfig();

    /**
     * 初始化
     */
    public void init() {
        disruptor = new Disruptor<>(eventFactory(), getQueueSize(), getThreadFactory(), getProducerType(), getStrategy());
        disruptor.setDefaultExceptionHandler(new MyHandlerException());
        // 这里确定这些消费者怎么消费 点对点 还是 发布订阅
        publishConfig();
        ringBuffer = disruptor.start();

        //初始化数据发布
        for (D data : initQueue) {
            ringBuffer.publishEvent(new EventTranslatorOneArg<E, D>() {
                public void translateTo(E event, long sequence, D data) {
                    event.setValue(data);
                }
            }, data);
        }
        this.clearQueueHelper();
    }

    /**
     * 加入资源清理钩子 单纯添加钩子线程，开始不运行，等服务关闭时会清理线程池
     */
    protected final void clearQueueHelper() {
        synchronized (queueHelperList) {
            if (queueHelperList.isEmpty()) {
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    for (BaseQueueHelper baseQueueHelper : queueHelperList) {
                        baseQueueHelper.shutdown();
                    }
                }));
            }
            queueHelperList.add(this);
        }
    }

    protected abstract ThreadFactory getThreadFactory();

    /**
     * 如果要改变线程执行优先级，override此策略. YieldingWaitStrategy会提高响应并在闲时占用70%以上CPU，
     * 慎用SleepingWaitStrategy会降低响应更减少CPU占用，用于日志等场景.
     *
     * @return WaitStrategy
     */
    protected abstract WaitStrategy getStrategy();

    /**
     * 插入队列消息，支持在对象init前插入队列，则在队列建立时立即发布到队列处理.
     */
    public synchronized void publishEvent(D data) {
        if (ringBuffer == null) {
            initQueue.add(data);
            return;
        }
        ringBuffer.publishEvent(new EventTranslatorOneArg<E, D>() {
            public void translateTo(E event, long sequence, D data) {
                event.setValue(data);
            }
        }, data);
    }

    /**
     * 关闭队列
     */
    public void shutdown() {
        disruptor.shutdown();
    }

    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub

    }

}

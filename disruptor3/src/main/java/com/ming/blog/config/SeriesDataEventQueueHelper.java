package com.ming.blog.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.WorkHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component
public class SeriesDataEventQueueHelper extends BaseQueueHelper<SeriesData, SeriesDataEvent, SeriesDataEventHandler> implements InitializingBean {

    private static final int QUEUE_SIZE = 1024;

    @Resource
    private List<SeriesDataEventHandler> seriesDataEventHandler;


    @Override
    protected int getQueueSize() {
        return QUEUE_SIZE;
    }

    @Override
    protected EventFactory eventFactory() {
        return new EventFactory() {
            @Override
            public Object newInstance() {
                return null;
            }
        };
    }

    @Override
    protected WorkHandler[] getHandler() {
        int size = seriesDataEventHandler.size();
        SeriesDataEventHandler[] paramEventHandlers = (SeriesDataEventHandler[]) seriesDataEventHandler.toArray(new SeriesDataEventHandler[size]);
        return paramEventHandlers;
    }

    @Override
    protected WaitStrategy getStrategy() {
        return new BlockingWaitStrategy();
        //return new YieldingWaitStrategy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}
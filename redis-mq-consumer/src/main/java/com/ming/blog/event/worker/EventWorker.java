package com.ming.blog.event.worker;

import com.alibaba.fastjson.JSON;
import com.ming.blog.event.channel.RedisEventChannel;
import com.ming.blog.event.entity.Event;
import com.ming.blog.event.executor.BaseDaemonWorker;
import com.ming.blog.event.executor.EventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jiangzaiming
 */
@Slf4j
@Component
@Order(2)
public class EventWorker extends BaseDaemonWorker {

    @Resource
    private RedisEventChannel eventChannel;

    @Resource
    private EventExecutor eventExecutor;

    @Override
    public void run() {
        checkUnprocessedEvent();
        process();
    }

    private void checkUnprocessedEvent() {
        Map<String, Event> processingEvents = eventChannel.getProcessingEvent();
        for (Map.Entry<String, Event> event : processingEvents.entrySet()) {
            String key = event.getKey();
            if (StringUtils.isNotEmpty(key)) {
                String[] name = key.split("-");
                Event value = event.getValue();
                if (name.length == 2) {
                    eventExecutor.executorFunction(name[1], value);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("sleep error", e);
            }
        }
    }

    @Override
    protected void process() {
        while (true) {
            Event event = null;
            try {
                event = eventChannel.consumer();
                if (event == null) {
                    // 没有对象休息100 毫秒
                    Thread.sleep(100);
                } else {
                    log.info("[Consume] {}", JSON.toJSONString(event));
                    eventExecutor.executor(event);
                }
            } catch (Exception e) {
                log.error("consume event error {} {}", e, JSON.toJSONString(event));
            }
        }
    }
}

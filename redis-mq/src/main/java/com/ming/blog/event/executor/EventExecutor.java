package com.ming.blog.event.executor;

import com.alibaba.fastjson.JSON;
import com.ming.blog.event.Event;
import com.ming.blog.event.EventChannel;
import com.ming.blog.event.TraceId;
import com.ming.blog.event.base.FunctionMap;
import com.ming.blog.event.base.FunctionNameMap;
import com.ming.blog.event.function.BaseFunction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangzaiming
 */
@Slf4j
@Component
public class EventExecutor {

    @Resource
    ThreadPoolTaskExecutor taskExecutor;

    @Resource
    private EventChannel eventChannel;

    @Resource
    private FunctionMap functionMap;

    @Resource
    private FunctionNameMap functionNameMap;

    private List<BaseFunction> getProcessorFunction(int type) {
        return functionMap.getFunction(type);
    }

    @Async(value = "taskExecutor")
    public void executor(Event event) {
//        taskExecutor.execute(() -> {
        MDC.put("traceId", TraceId.id() + "-" + event.getEventId());
        List<BaseFunction> baseFunctionList = getProcessorFunction(event.getEventType());
        // 有对应的处理器
        if (baseFunctionList != null && baseFunctionList.size() > 0) {
            for (BaseFunction baseFunction : baseFunctionList) {
                functionInvoke(baseFunction, event);
            }
        } else {
            log.warn(String.format("no function to process this event, event type: %s , event message: %s",
                    event.getEventType(), JSON.toJSONString(event.getEventParams())));
        }
//        });
    }

    /**
     * @param baseFunction 方法
     * @param event        事件属性
     * @param <T>          参数泛型
     */
    private <T> void functionInvoke(BaseFunction<T> baseFunction, Event<T> event) {
        try {
            if (!checkEventTimeout(baseFunction, event)) {
                confirm(baseFunction.getName(), event.getEventId());
                log.info("event time out, event : {}", JSON.toJSONString(event));
                return;
            }
            Event<T> ev = Event.from(event);
            ev.setEventId(event.getEventId());
            T param = JSON.parseObject(JSON.toJSONString(event.getEventParams()), baseFunction.getType());
            ev.setEventParams(param);
            try {
                eventChannel.processing(baseFunction.getName(), ev);
            } catch (Exception e) {
                log.error(String.format("processing event error, event type: %s , event message: %s",
                        event.getEventType(), JSON.toJSONString(event.getEventParams())), e);
            }
            boolean success = baseFunction.execute(param);
            if (success) {
                confirm(baseFunction.getName(), event.getEventId());
            }
        } catch (Exception e) {
            log.error(String.format("process event error, event type: %s , event message: %s",
                    event.getEventType(), JSON.toJSONString(event.getEventParams())), e);
        }
    }

    private <T> boolean checkEventTimeout(BaseFunction<T> baseFunction, Event<T> event) {
        // 超时了，返回不处理
        if (baseFunction.getTimeout() == 0L) {
            // 方法未设置超时时间
            return true;
        } else {
            return System.currentTimeMillis() - event.getTs() <= baseFunction.getTimeout();
        }
    }

    public void confirm(String functionName, String eventId) {
        try {
            eventChannel.confirm(functionName, eventId);
        } catch (Exception e) {
            log.error(String.format("confirm event error, event id : %s, processor name : %s",
                    eventId, functionName), e);
        }
    }


    /**
     * 指定处理器处理event
     *
     * @param functionName 处理器名称
     * @param event        事件
     */
    public void executorFunction(String functionName, Event event) {
        if (event != null) {
            MDC.put("traceId", TraceId.id() + "-" + event.getEventId());
            BaseFunction baseFunction = getFunctionByName(functionName);
            if (baseFunction != null) {
                functionInvoke(baseFunction, event);
            } else {
                log.error("baseFunction not exist , name : {}, event : {}",
                        functionName, JSON.toJSONString(event));
            }
        }
    }

    private BaseFunction getFunctionByName(String functionName) {
        return functionNameMap.getFunction(functionName);
    }

}

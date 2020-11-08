package com.ming.blog.mq.event.base;

import com.alibaba.fastjson.JSON;
import com.ming.blog.mq.event.EventTypeEnum;
import com.ming.blog.mq.event.function.BaseFunction;
import com.ming.blog.mq.event.function.EventType;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author jiangzaiming
 */
@Slf4j
public abstract class BaseFunctionProcessor {

    @Resource
    private FunctionMap functionMap;

    @Resource
    private FunctionNameMap functionNameMap;

    @PostConstruct
    public <T> void initProcessor() {
        Method[] methods = this.getClass().getDeclaredMethods();
        BaseFunctionProcessor processor = this;
        for (Method method : methods) {
            if (method.isAnnotationPresent(EventType.class)) {
                EventType eventType = method.getAnnotation(EventType.class);
                Class[] types = method.getParameterTypes();
                if (types.length == 1) {
                    BaseFunction<T> baseFunction = new BaseFunction<T>(types[0], eventType.timeout()) {
                        @Override
                        public boolean execute(T param) {
                            try {
                                method.invoke(processor, param);
                                return true;
                            } catch (Exception e) {
                                log.error("", e);
                                log.error("processor invoke error {} {} {} {}",
                                        JSON.toJSONString(param), processor.getClass().getCanonicalName(), method.getName(), e);
                            }
                            return false;
                        }
                    };
                    String name = this.getClass().getCanonicalName() + "." + method.getName();
                    baseFunction.setName(name);
                    functionNameMap.setFunction(name, baseFunction);
                    EventTypeEnum[] eventTypes = eventType.types();
                    if (eventTypes.length > 0) {
                        // 多个事件
                        for (EventTypeEnum type : eventTypes) {
                            functionMap.setFunction(type.getEventType(), baseFunction);
                        }
                    } else {
                        // 单个事件
                        functionMap.setFunction(eventType.type().getEventType(), baseFunction);
                    }
                }
            }
        }
    }
}

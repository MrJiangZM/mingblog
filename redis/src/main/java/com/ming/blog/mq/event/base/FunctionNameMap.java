package com.ming.blog.mq.event.base;

import com.google.common.collect.Maps;
import com.ming.blog.mq.event.function.BaseFunction;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author jiangzaiming
 */
@Component
public class FunctionNameMap {

    private Map<String, BaseFunction> functionMap = Maps.newConcurrentMap();

    public BaseFunction getFunction(String name) {
        return functionMap.get(name);
    }

    public void setFunction(String name, BaseFunction baseFunction) {
        functionMap.put(name, baseFunction);
    }
}

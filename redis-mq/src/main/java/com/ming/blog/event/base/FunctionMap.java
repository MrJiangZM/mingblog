package com.ming.blog.event.base;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ming.blog.event.function.BaseFunction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiangzaiming
 */
@Component
public class FunctionMap {

    private Map<Integer, List<BaseFunction>> functionMap = Maps.newHashMap();

    private ReentrantLock lock = new ReentrantLock();

    public List<BaseFunction> getFunction(int type) {
        return functionMap.get(type);
    }

    public void setFunction(int type, BaseFunction baseFunction) {
        lock.lock();
        try {
            List<BaseFunction> list = getFunction(type);
            if (list == null) {
                list = Lists.newArrayList();
                functionMap.put(type, list);
            }
            list.add(baseFunction);
        } finally {
            lock.unlock();
        }
    }
}

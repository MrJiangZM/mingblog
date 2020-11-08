package com.ming.blog.event.processor;

import com.ming.blog.event.base.BaseFunctionProcessor;
import com.ming.blog.event.entity.EventTypeEnum;
import com.ming.blog.event.function.EventType;
import com.ming.blog.controller.Test;

/**
 * @author Jiang Zaiming
 * @date 2020/6/4 6:08 下午
 */
public class Test2Processor extends BaseFunctionProcessor {

    @EventType(type = EventTypeEnum.UNKNOWN)
    public void store2(Test param) {
        System.out.println("+++++++++++++++");
        System.out.println(param);
    }


}

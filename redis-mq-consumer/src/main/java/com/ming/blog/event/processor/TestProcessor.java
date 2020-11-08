package com.ming.blog.event.processor;

import com.ming.blog.controller.Test;
import com.ming.blog.event.base.BaseFunctionProcessor;
import com.ming.blog.event.entity.EventTypeEnum;
import com.ming.blog.event.function.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Jiang Zaiming
 * @date 2020/6/4 5:59 下午
 */
@Slf4j
@Component
public class TestProcessor extends BaseFunctionProcessor {

    @EventType(type = EventTypeEnum.MOTION_DETECTION_TASK)
    public void store1(Test param) {
        System.out.println("===============");
        System.out.println(param);
    }

}

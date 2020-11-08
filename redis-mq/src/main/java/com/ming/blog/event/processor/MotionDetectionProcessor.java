package com.ming.blog.event.processor;//package com.ming.blog.mq.event.processor;

import com.ming.blog.entity.Test;
import com.ming.blog.event.EventTypeEnum;
import com.ming.blog.event.base.BaseFunctionProcessor;
import com.ming.blog.event.function.EventType;
import com.ming.blog.event.param.MotionDetectionTaskParam;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.Primes;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MotionDetectionProcessor extends BaseFunctionProcessor {

    @EventType(type = EventTypeEnum.MOTION_DETECTION_TASK)
    public void store(Test param) {
        System.out.println("===============");
        System.out.println(param);
    }
}

package com.ming.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/11</pre>
 */
@Slf4j
@Service
public class TestService {

    @Async
    public String testTrace() throws InterruptedException {
        Thread.sleep(5000L);
        log.info("zhe doushisha:{}", MDC.get("traceId"));
        return "test";
    }

}

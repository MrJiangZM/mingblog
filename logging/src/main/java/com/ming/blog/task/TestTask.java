package com.ming.blog.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    @Scheduled(fixedDelay = 5000)
    public void test1() {
        System.out.println("=================================");
        log.info("try to test info");
        log.warn("warn warn warn");
        log.debug("debug debug debug");
        log.error("error error error");
    }

}

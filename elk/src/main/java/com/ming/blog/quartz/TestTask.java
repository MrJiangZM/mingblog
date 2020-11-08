package com.ming.blog.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

//    @Scheduled(fixedDelay = 5000)
    public void test1() {
        System.out.println("=================================");
        log.info("------------------------try to test info");
        log.warn("------------------------warn warn warn");
        log.debug("------------------------debug debug debug");
        log.error("------------------------error error error");
    }

//    @Scheduled(fixedDelay = 3000)
    public void test2() {
        try {
            log.info("try to create error");
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("error happened {}", e);
        }
    }

}

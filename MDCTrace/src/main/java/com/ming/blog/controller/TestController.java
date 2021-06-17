package com.ming.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/11</pre>
 */
@Slf4j
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("test")
    public Object test() throws InterruptedException {
        testService.testTrace();
        return "test trace Id";
    }

}

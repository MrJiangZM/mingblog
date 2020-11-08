package com.ming.blog.controller;

import com.ming.blog.limit.ApiLimiting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LimitingTestController {



    @GetMapping("/limit")
    @ApiLimiting(count = 2, time = 10000000L)
    public String test(@RequestHeader Integer userId) {
        log.info("来了啊========================");
        return "Test";
    }

}

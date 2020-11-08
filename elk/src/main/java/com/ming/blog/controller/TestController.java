package com.ming.blog.controller;

import com.google.common.collect.Lists;
import com.ming.blog.config.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/testget")
    public String test(HttpServletRequest request) {
        System.out.println("=========");
        RequestWrapper request1 = (RequestWrapper) request;
        System.out.println(request1.getId());
        log.info("hahahaha");
        return "gagagagaga";
    }

    @PostMapping("/testpost")
    public Object testpost(HttpServletRequest request,
                           @RequestBody Map map) {
        System.out.println("=========");
        RequestWrapper request1 = (RequestWrapper) request;
        System.out.println(request1.getId());
        log.info("hahahaha");
        return Lists.newArrayList("啦啦啦");
    }

}

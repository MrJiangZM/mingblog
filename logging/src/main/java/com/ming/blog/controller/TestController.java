package com.ming.blog.controller;

import com.ming.blog.service.StudentLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class TestController {

    @Autowired
    private StudentLogService studentLogService;

    @GetMapping("/test")
    public Object test(@RequestParam("test") String test) {
        log.info("info info info");
        log.error("error error error");
        Object all = studentLogService.findAll();
        log.warn("warn warn warn");
        return all;
    }

}

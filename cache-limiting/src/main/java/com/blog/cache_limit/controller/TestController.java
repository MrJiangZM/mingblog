package com.blog.cache_limit.controller;

import com.blog.cache_limit.domain.User;
import com.blog.cache_limit.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public void test() {
        User user = testService.getUser(User.builder().id(1).build());
        System.out.println(user);
    }

}

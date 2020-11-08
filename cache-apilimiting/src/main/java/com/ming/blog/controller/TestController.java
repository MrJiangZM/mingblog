package com.ming.blog.controller;

import com.ming.blog.domain.User;
import com.ming.blog.service.CacheService;
import com.ming.blog.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/find")
    public void find(Integer id) {
        User user =  testService.findByUserId(id);
    }

    @GetMapping("/put")
    public void put(Integer id) {
        User user = User.builder().id(id).name("小明").build();
        testService.putUserIntoCache(user);
    }

    @GetMapping("/del")
    public void del(Integer id) {
        User user = User.builder().id(id).name("小明").build();
        testService.delUserIntoCache(user);
    }

}

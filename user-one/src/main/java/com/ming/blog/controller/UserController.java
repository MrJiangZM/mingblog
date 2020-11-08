package com.ming.blog.controller;

import com.ming.blog.domain.SysUser;
import com.ming.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 6:03 下午
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody SysUser user) {
        userService.add(user);
    }

    @PostMapping("/info")
    public void update(@RequestBody SysUser user) {
        userService.add(user);
    }


}

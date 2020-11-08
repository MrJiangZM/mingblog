package com.ming.blog.controller;

import com.ming.blog.entity.SysUserRole;
import com.ming.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:43 上午
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public Object info(@RequestHeader("userId") Long userId) {
        return userService.getUserInfo(userId);
    }

    /**
     * 给某个用户添加某个角色
     */
    @PostMapping("/addRole")
    public void addRole(@RequestBody SysUserRole userRole) {
        userService.addRole(userRole);
    }

}

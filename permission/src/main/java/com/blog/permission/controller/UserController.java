package com.blog.permission.controller;

import com.blog.permission.domain.Role;
import com.blog.permission.domain.User;
import com.blog.permission.povo.GroupPO;
import com.blog.permission.povo.UserPO;
import com.blog.permission.service.GroupService;
import com.blog.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public void add(@RequestBody UserPO userPO){
        userService.addUser(userPO);
    }

    @PostMapping("/del")
    public void del(Integer userId){
        userService.delUser(userId);
    }

    // 指定用户的组
    @PostMapping("/assign")
    public void assignGroup(@RequestBody GroupPO groupPO) {
        groupService.assign(groupPO);
    }



}

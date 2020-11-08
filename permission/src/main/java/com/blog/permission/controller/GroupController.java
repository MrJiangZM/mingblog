package com.blog.permission.controller;

import com.blog.permission.povo.GroupPO;
import com.blog.permission.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public void add(GroupPO groupPO) {
        groupService.add(groupPO);
    }

    @PostMapping("/del")
    public void del(Integer groupId) {
        groupService.del(groupId);
    }




}

package com.ming.blog.controller;

import com.google.common.collect.Maps;
import com.ming.blog.pojo.secondary.User;
import com.ming.blog.service.primary.StudentService;
import com.ming.blog.service.secondary.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public Object findAll() {
        List<User> users = userService.findAll();
        HashMap<String, Object> students = studentService.findAll();
        Map<String, Object> map = Maps.newHashMap();
        map.put("users", users);
        map.put("students", students);
        return students;
    }

    @GetMapping("/insert")
//    @Transactional("secondaryTransactionManager")
    public Object insert() {
        userService.insert();
        studentService.insert();
        return "ok";
    }


}

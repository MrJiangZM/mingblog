package com.ming.blog.controller;

import com.google.common.collect.Lists;
import com.ming.blog.config.DataSourceContextHolder;
import com.ming.blog.db1.dao.StudentJtaAopDao;
import com.ming.blog.db1.domain.StudentJtaAop;
import com.ming.blog.db1.service.StudentJtaAopService;
import com.ming.blog.db2.dao.UserJtaAopDao;
import com.ming.blog.db2.domain.UserJtaAop;
import com.ming.blog.db2.service.UserJtaAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aop")
public class TestController {

    @Autowired
    private UserJtaAopService userJtaAopService;
    @Autowired
    private StudentJtaAopService studentJtaAopService;
    @Autowired
    private UserJtaAopDao userJtaAopDao;
    @Autowired
    private StudentJtaAopDao studentJtaAopDao;

    @GetMapping("/test")
    public Object test() {
        System.out.println(DataSourceContextHolder.getDataSourceKey());
        List<UserJtaAop> all = userJtaAopService.findAll();
        System.out.println(DataSourceContextHolder.getDataSourceKey());
        List<StudentJtaAop> all1 = studentJtaAopService.findAll();
        System.out.println(DataSourceContextHolder.getDataSourceKey());
        ArrayList<Object> objects = Lists.newArrayList(all, all1);
        return objects;
    }

    @GetMapping("/insert")
    @Transactional
    public Object insert() {
        UserJtaAop jtaAop = new UserJtaAop();
        jtaAop.setName("lala");
        userJtaAopService.insert(jtaAop);
        int i = 1 / 0;
        StudentJtaAop studentJtaAop = new StudentJtaAop();
        studentJtaAop.setName("gagaga");
        studentJtaAopService.insert(studentJtaAop);


        return null;
    }

    @GetMapping("/insert/one")
    @Transactional(transactionManager = "transactionManager")
    public Object insertOne() {
        UserJtaAop jtaAop = new UserJtaAop();
        jtaAop.setName("lala");
        userJtaAopService.insert(jtaAop);
        int i = 1 / 0;
        return null;
    }

    @GetMapping("/update")
    @Transactional(transactionManager = "transactionManager")
    public Object update() {
        UserJtaAop jtaAop = new UserJtaAop();
        jtaAop.setName("lala");
        userJtaAopDao.insert(jtaAop);
        int i = 1 / 0;
        StudentJtaAop studentJtaAop = new StudentJtaAop();
        studentJtaAop.setName("gagaga");
        studentJtaAopDao.insert(studentJtaAop);


        return null;
    }

}

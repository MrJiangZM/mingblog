package com.ming.blog.service.primary.impl;

import com.google.common.collect.Maps;
import com.ming.blog.dao.StudentDao;
import com.ming.blog.pojo.primary.Student;
import com.ming.blog.pojo.secondary.User;
import com.ming.blog.service.primary.StudentService;
import com.ming.blog.service.secondary.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserService userService;

    @Override
//    @Transactional(transactionManager = "primaryTransactionManager")
    public HashMap<String, Object> findAll() {
        List<User> all = userService.findAll();
        List<Student> all1 = studentDao.findAll();
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("user", all);
        objectObjectHashMap.put("Student", all1);
        return objectObjectHashMap;
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void insert() {
        Student s = new Student();
        s.setName("haha");
        studentDao.insert(s);

        int r = 1/0;
//        userService.insert(); // 为了验证事务的管理的东西

    }
}

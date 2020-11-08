package com.ming.blog.service.primary.impl;

import com.google.common.collect.Maps;
import com.ming.blog.dao.primary.StudentJtaDao;
import com.ming.blog.pojo.primary.StudentJta;
import com.ming.blog.pojo.secondary.UserJta;
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
    private StudentJtaDao studentDao;
    @Autowired
    private UserService userService;

    @Override
//    @Transactional(transactionManager = "primaryTransactionManager")
    public HashMap<String, Object> findAll() {
        List<UserJta> all = userService.findAll();
        List<StudentJta> all1 = studentDao.findAll();
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("userjta", all);
        objectObjectHashMap.put("StudentJta", all1);
        return objectObjectHashMap;
    }

    @Override
    @Transactional
//    @Transactional(transactionManager = "transactionManager")
    public void insert() {
        StudentJta s = new StudentJta();
        s.setName("haha");
        studentDao.insert(s);
//        userService.insert(); 为了验证事务的管理的东西

    }

    @Override
    public List<StudentJta> findAllS() {
        return studentDao.findAll();
    }
}

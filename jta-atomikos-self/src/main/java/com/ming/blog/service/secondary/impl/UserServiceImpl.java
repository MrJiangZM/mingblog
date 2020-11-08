package com.ming.blog.service.secondary.impl;

import com.ming.blog.dao.secondary.UserJtaDao;
import com.ming.blog.pojo.secondary.UserJta;
import com.ming.blog.service.secondary.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJtaDao userDao;

    @Override
//    @Transactional(transactionManager = "secondaryTransactionManager")
    public List<UserJta> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
//    @Transactional(transactionManager = "transactionManager")
    public void insert() {
        UserJta u = new UserJta();
        u.setName("haha");
        userDao.insert(u);
    }
}

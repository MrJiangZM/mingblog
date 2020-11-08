package com.ming.blog.service.secondary.impl;

import com.ming.blog.dao.UserDao;
import com.ming.blog.pojo.secondary.User;
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
    private UserDao userDao;

    @Override
//    @Transactional(transactionManager = "secondaryTransactionManager")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional("secondaryTransactionManager")
    public void insert() {
        User u = new User();
        u.setName("haha");
        userDao.insert(u);

    }
}

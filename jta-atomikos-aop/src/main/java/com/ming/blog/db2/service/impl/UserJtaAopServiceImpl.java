package com.ming.blog.db2.service.impl;

import com.ming.blog.db2.dao.UserJtaAopDao;
import com.ming.blog.db2.domain.UserJtaAop;
import com.ming.blog.db2.service.UserJtaAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserJtaAopServiceImpl implements UserJtaAopService {

    @Autowired
    private UserJtaAopDao userJtaAopDao;

    @Override
    public List<UserJtaAop> findAll() {
        return userJtaAopDao.findAll();
    }

    @Transactional(transactionManager = "transactionManager")
    public int insert(UserJtaAop userJtaAop) {
        return userJtaAopDao.insert(userJtaAop);
    }

}

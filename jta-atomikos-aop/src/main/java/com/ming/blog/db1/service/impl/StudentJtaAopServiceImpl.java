package com.ming.blog.db1.service.impl;

import com.ming.blog.db1.dao.StudentJtaAopDao;
import com.ming.blog.db1.domain.StudentJtaAop;
import com.ming.blog.db1.service.StudentJtaAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentJtaAopServiceImpl implements StudentJtaAopService {

    @Autowired
    private StudentJtaAopDao studentJtaAopDao;

    @Override
    public List<StudentJtaAop> findAll() {
        return studentJtaAopDao.findAll();
    }

    @Transactional(transactionManager = "transactionManager")
    public int insert(StudentJtaAop studentJtaAop) {
        return studentJtaAopDao.insert(studentJtaAop);
    }

}

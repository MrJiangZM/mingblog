package com.ming.blog.service.impl;

import com.google.common.collect.Lists;
import com.ming.blog.money.dao.primary.StudentMoneyDao;
import com.ming.blog.money.dao.primary.TeacherMoneyDao;
import com.ming.blog.money.dao.secondary.RoleMoneyDao;
import com.ming.blog.money.dao.secondary.UserMoneyDao;
import com.ming.blog.money.pojo.primary.StudentMoney;
import com.ming.blog.money.pojo.primary.TeacherMoney;
import com.ming.blog.money.pojo.secondary.RoleMoney;
import com.ming.blog.money.pojo.secondary.UserMoney;
import com.ming.blog.order.dao.primary.StudentOrderDao;
import com.ming.blog.order.dao.primary.TeacherOrderDao;
import com.ming.blog.order.dao.secondary.RoleOrderDao;
import com.ming.blog.order.dao.secondary.UserOrderDao;
import com.ming.blog.order.pojo.primary.StudentOrder;
import com.ming.blog.order.pojo.primary.TeacherOrder;
import com.ming.blog.order.pojo.secondary.RoleOrder;
import com.ming.blog.order.pojo.secondary.UserOrder;
import com.ming.blog.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private StudentMoneyDao studentMoneyDao;
    @Autowired
    private StudentOrderDao studentOrderDao;
    @Autowired
    private TeacherMoneyDao teacherMoneyDao;
    @Autowired
    private TeacherOrderDao teacherOrderDao;
    @Autowired
    private UserMoneyDao userMoneyDao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private RoleMoneyDao roleMoneyDao;
    @Autowired
    private RoleOrderDao roleOrderDao;

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public List<Object> findAll() {
        List<StudentMoney> all = studentMoneyDao.findAll();
        List<StudentOrder> all1 = studentOrderDao.findAll();
        List<TeacherMoney> all2 = teacherMoneyDao.findAll();
        List<TeacherOrder> all3 = teacherOrderDao.findAll();
        List<UserMoney> all4 = userMoneyDao.findAll();
        List<UserOrder> all5 = userOrderDao.findAll();
        List<RoleMoney> all6 = roleMoneyDao.findAll();
        List<RoleOrder> all7 = roleOrderDao.findAll();
        return Lists.newArrayList(all, all1, all2, all3, all4, all5, all6, all7);
    }
}

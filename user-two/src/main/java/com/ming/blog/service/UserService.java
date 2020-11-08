package com.ming.blog.service;

import com.ming.blog.dao.UserDao;
import com.ming.blog.entity.SysUser;
import com.ming.blog.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:41 上午
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public SysUser getUserInfo(Long userId) {
        return userDao.findById(userId).orElseThrow(() ->
                new RuntimeException("找不到对应用户"));
    }

    public void addRole(SysUserRole userRole) {

    }
}

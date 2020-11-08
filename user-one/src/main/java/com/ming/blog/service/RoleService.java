package com.ming.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 11:44 上午
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;


    public List<Role> getRolesByUserId(Integer userId) {
        return roleDao.findByUserId(userId);
    }

}

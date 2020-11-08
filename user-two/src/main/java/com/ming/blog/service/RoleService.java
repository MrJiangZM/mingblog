package com.ming.blog.service;

import com.ming.blog.dao.RoleDao;
import com.ming.blog.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:41 上午
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<SysRole> findRoleByUserId(Long userId) {
        return roleDao.findRoleByUserId(userId);
    }
}

package com.blog.permission.service;

import com.blog.permission.domain.Role;
import com.blog.permission.povo.RolePO;

import java.util.List;

public interface RoleService {
    List<Role> getRoleByUser(Integer userId);

    List<Role> getAllRole();

    void add(RolePO rolePO);

    void del(Integer roleId);
}

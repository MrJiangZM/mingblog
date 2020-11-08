package com.blog.permission.service;

import com.blog.permission.domain.Role;
import com.blog.permission.povo.UserPO;

import java.util.List;

public interface UserService {
    void addUser(UserPO user);

    List<Role> getRoleByUser(Integer userId);

    void delUser(Integer userId);
}

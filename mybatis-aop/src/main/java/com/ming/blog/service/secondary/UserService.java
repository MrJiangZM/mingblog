package com.ming.blog.service.secondary;

import com.ming.blog.pojo.secondary.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void insert();
}

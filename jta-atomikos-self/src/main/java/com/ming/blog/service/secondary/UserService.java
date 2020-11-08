package com.ming.blog.service.secondary;

import com.ming.blog.pojo.secondary.UserJta;

import java.util.List;

public interface UserService {

    List<UserJta> findAll();

    void insert();
}

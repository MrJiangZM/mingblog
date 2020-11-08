package com.ming.blog.service;

import com.ming.blog.domain.User;

public interface TestService {


    User findByUserId(Integer id);

    User putUserIntoCache(User user);

    void delUserIntoCache(User user);

}

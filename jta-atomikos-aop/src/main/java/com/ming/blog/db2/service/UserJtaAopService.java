package com.ming.blog.db2.service;

import com.ming.blog.db2.domain.UserJtaAop;

import java.util.List;

public interface UserJtaAopService {
    List<UserJtaAop> findAll();
    int insert(UserJtaAop userJtaAop);
}

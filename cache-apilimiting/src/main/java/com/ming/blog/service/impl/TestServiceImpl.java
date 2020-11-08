package com.ming.blog.service.impl;

import com.ming.blog.domain.User;
import com.ming.blog.service.TestService;
import lombok.extern.slf4j.Slf4j;

import com.ming.blog.service.TestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Override
    @Cacheable(value = "USER", key = "#id", sync = true/*, condition = "#id==2"*/)
    public User findByUserId(Integer id) {
        log.info("新建出来的==============");
        User user = User.builder().id(id).name("小明").build();
        return user;
    }

    @Override
    @CachePut(value = "test3", key = "#user.id")
    public User putUserIntoCache(User user) {
        log.info("添加到了cache中");
        return user;
    }

    @Override
    @CacheEvict(value = "test3", key = "#user.id")
    public void delUserIntoCache(User user) {
        log.info("删除id为1的数据");
    }

}

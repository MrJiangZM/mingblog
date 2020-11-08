package com.ming.blog.service;

import com.ming.blog.domain.User;

import java.util.LinkedList;

public interface CacheService {

    LinkedList<Long> addCache(String s, LinkedList<Long> apiQueue);

}

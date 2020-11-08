package com.ming.blog.service;

import com.ming.blog.cache.basepack.BaseValueCache;

public class RedisValue extends BaseValueCache<String, String> {

    private static final String task = "ming:task:";

    @Override
    protected String getPrefix() {
        return task;
    }
}

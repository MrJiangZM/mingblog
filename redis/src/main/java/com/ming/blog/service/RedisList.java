package com.ming.blog.service;

import com.ming.blog.cache.basepack.BaseListCache;

public class RedisList extends BaseListCache<String, String> {
    @Override
    protected String getPrefix() {
        return "ming:list:";
    }
}

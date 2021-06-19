package com.ming.blog.config;

import java.io.Serializable;

public abstract class ValueWrapper<T> implements Serializable {

    /**
     * 队列中的版本信息 version  trace  消息id（幂等性处理）
     */
    private String trace;
    private Long version;

    private T value;

    public ValueWrapper() {
    }

    public ValueWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
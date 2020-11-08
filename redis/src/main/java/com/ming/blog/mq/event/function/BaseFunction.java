package com.ming.blog.mq.event.function;


import java.lang.reflect.Type;

/**
 * @author jiangzaiming
 */
public abstract class BaseFunction<T> {

    private Type type;

    private String name;

    private long timeout;

    protected BaseFunction(Type type, long timeout) {
        this.type = type;
        this.timeout = timeout;
    }

    /**
     * 执行
     *
     * @param param 参数
     *
     * @return 执行结果
     */
    public abstract boolean execute(T param);

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
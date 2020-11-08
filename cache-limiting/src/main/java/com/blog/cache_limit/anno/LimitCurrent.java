package com.blog.cache_limit.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitCurrent {

    int time();

    int count();

    int waits();

}

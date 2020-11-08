package com.ming.blog.limit;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiLimiting {

    long time() default Integer.MAX_VALUE;

    int count() default Integer.MAX_VALUE;

}

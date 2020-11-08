package com.ming.blog.anno;

import java.lang.annotation.*;

@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Inherited 允许子类继承父类的注解
public @interface JustTest {

    public String value() default "默认的信息";
    public String description() default "no description";

}

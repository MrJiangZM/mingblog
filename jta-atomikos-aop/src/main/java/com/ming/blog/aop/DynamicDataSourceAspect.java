package com.ming.blog.aop;

import com.ming.blog.anno.DataSourceTypeAnno;
import com.ming.blog.config.DataSourceContextHolder;
import com.ming.blog.constant.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(* com.ming.blog.*.dao.*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before(value = "dataSourcePointCut()")
    public void beforeSwitchDS(JoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSourceTypeAnno methodAnnotation = method.getAnnotation(DataSourceTypeAnno.class);
        System.out.println(Data.PRIMARY_DATASOURCE.getValue());
        System.out.println(methodAnnotation.value());
        if (methodAnnotation == null || methodAnnotation.value() == null
                || methodAnnotation.value().equals(Data.PRIMARY_DATASOURCE.getValue())) {
            DataSourceContextHolder.setDataSourceKey(Data.PRIMARY_DATASOURCE.getValue());
        } else {
            DataSourceContextHolder.setDataSourceKey(Data.SECONDARY_DATASOURCE.getValue());
        }
    }

    @After(value = "dataSourcePointCut()")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDataSourceKey();
    }

}

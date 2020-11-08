package com.ming.blog.limit;

import com.ming.blog.service.LimitService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ApiLimitingAop {

    @Autowired
    private LimitService limitService;

    @Pointcut(value = "execution(* com.ming.blog.controller.*.*(..))")
    public void apiLimiting() {
    }

    @Around(value = "apiLimiting()")
    public Object beforeSwitchDS(ProceedingJoinPoint point) throws Throwable {

        String className;
        String methodName;
        String userId;

        className = point.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        methodName = method.getName();

        ApiLimiting methodAnnotation = method.getAnnotation(ApiLimiting.class);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes != null ? requestAttributes.getRequest() : null;
        if (request == null) {
            return null;
        }
        userId = request.getHeader("userId");

        String cacheKey = className + methodName + userId;

        if (methodAnnotation == null) {
            return point.proceed();
        } else {
            int count = methodAnnotation.count();
            long time = methodAnnotation.time();
            boolean pass = limitService.judgeLimit(cacheKey, count, time);
            return pass ? point.proceed() : null;
        }
    }

}

package com.ming.blog.interceptconfig;

import com.google.common.collect.Sets;
import com.ming.blog.anno.JustTest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class TestInterceptor1 extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor1111 preHandler=== 鉴权开始");
        if (checkLoginAndPermssion(handler)) {
            return true;
        }
        return false;
    }

    private boolean checkLoginAndPermssion(Object handler) {
        Set<String> set = Sets.newConcurrentHashSet();
        set.add("ok");
        set.add("yes");
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            JustTest annotation = method.getMethod().getAnnotation(JustTest.class);
            if(annotation == null) {
                annotation = method.getMethod().getDeclaringClass().getAnnotation(JustTest.class);
            }

            if(annotation != null) {
                if (StringUtils.isNotEmpty(annotation.value())) {
                    return set.contains(annotation.value());
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor1111 postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("interceptor1111 afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}

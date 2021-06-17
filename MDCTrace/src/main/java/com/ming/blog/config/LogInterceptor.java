package com.ming.blog.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mlamp.pingshan.config.web.annotation.Permission;
import com.mlamp.pingshan.sys_log.dao.SysLogDao;
import com.mlamp.pingshan.sys_log.entity.SysLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
https://www.jianshu.com/p/bd8757b593a2
 */
public class LogInterceptor implements HandlerInterceptor {
    //请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";
    //请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";

    private boolean check(Object o, HttpServletRequest request) {
        if(o instanceof HandlerMethod){
            Permission permission = ((HandlerMethod) o).getMethod().getAnnotation(Permission.class);
            return permission != null && permission.log() && StringUtils.isNotEmpty(request.getHeader("userId"));
        }else {
            return false;
        }

    }

    /**
     * 进入SpringMVC的Controller之前开始记录日志实体
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param o
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (!check(o, request)) {
            return true;
        }
        /**
         * 获取注解上的参数
         */
        Permission permission = ((HandlerMethod) o).getMethod().getAnnotation(Permission.class);
        //创建日志实体
        SysLog sysLog = new SysLog();

        //获取请求参数信息
        String param = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);

        sysLog.setOperationCode(permission.code());
        sysLog.setOperationName(permission.name());

        //设置请求参数
        sysLog.setParams(param);

        // 设置IP地址
        sysLog.setIp(request.getHeader("x-real-ip"));

        //设置请求方法,GET,POST...
        sysLog.setMethod(request.getMethod());

        //设置请求路径
        sysLog.setUrl(request.getRequestURI());

        sysLog.setCreateTime(System.currentTimeMillis());

        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());

        //设置请求实体到request内，方便afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY, sysLog);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        if (!check(o, request)) {
            return;
        }
        //得到bean
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        SysLogDao sysLogDao = factory.getBean(SysLogDao.class);

        //当前时间
        long currentTime = System.currentTimeMillis();

        //请求开始时间
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());

        //获取本次请求日志实体
        SysLog sysLog = (SysLog) request.getAttribute(LOGGER_ENTITY);

        //设置访问者
        sysLog.setUserId(Integer.parseInt(request.getHeader("userId")));

        //设置请求时间差
        sysLog.setTime(Integer.valueOf((currentTime - time) + ""));

        //执行将日志写入数据库，可以根据实际需求进行保存
//        if(!sysLog.getMethod().equals("GET")){
//
//        }
        sysLogDao.save(sysLog);
    }
}
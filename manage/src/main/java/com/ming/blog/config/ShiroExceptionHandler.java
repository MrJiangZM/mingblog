package com.ming.blog.config;

import com...exception.RestResponseStatus;
import com...web.CommonResponse;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(value = ShiroException.class)
    @ResponseBody
    public CommonResponse resolveException(ShiroException e) {

        if (e instanceof UnauthenticatedException) {
            return new CommonResponse(RestResponseStatus.USER_TOKEN_INVALID, "请重新登陆");
        } else if (e instanceof UnauthorizedException) {
            return new CommonResponse(RestResponseStatus.USER_AUTH_LIMIT, "当前用户没有访问权限");
        } else {
            return new CommonResponse(RestResponseStatus.SHIRO_AUTO_FAILED, "权限访问异常");
        }
    }

}

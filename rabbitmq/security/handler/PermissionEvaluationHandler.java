package com.ming.blog.config.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 4:06 下午
 */
@Slf4j
@Service("permissionEvaluator")
public class PermissionEvaluationHandler {

    public Boolean check(Authentication authentication) {
        System.out.println("进入了自定义的权限匹配器" + authentication);
        return true;
    }

}

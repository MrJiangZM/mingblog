package com.ming.blog.config;

import com...enums.UserRoleTypeEnum;
import com...exception.RestResponseStatus;
import com...exception.ServerException;
import com...pojo.User;
import com...pojo.UserRole;
import com...service.UserRoleService;
import com...service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    UserRoleService userRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

//        //把principals放session中 该手机号用户所拥有的权限key=userId value=principals
//        SecurityUtils.getSubject().getSession().setAttribute(userName, SecurityUtils.getSubject().getPrincipals());
        SecurityUtils.getSubject().getSession().setAttribute("user_name", userName);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userService.findOneByTelephone(userName);
        if (user != null) {
            UserRole userRole = userRoleService.getUserRole(user.getId());
            if (userRole != null) {
                UserRoleTypeEnum userRoleTypeEnum = UserRoleTypeEnum.valueOf(userRole.getType());
                info.addRole(userRoleTypeEnum.getEn());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());

        PasswordService svc = new DefaultPasswordService();
        User user = userService.findOneByTelephone(userName);
        if (user != null && svc.passwordsMatch(password, user.getPassword())) {
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
        } else {
            throw new ServerException(RestResponseStatus.USER_NOT_EXIST, "用户不存在");
        }
    }

}

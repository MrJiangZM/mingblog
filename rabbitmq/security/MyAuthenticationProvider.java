package com.ming.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @author Jiang Zaiming
 * @date 2020/4/1 12:51 下午
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DBUserDetailService dbUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名;
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码；
        String password = (String) authentication.getCredentials();
        // 这里调用我们的自己写的获取用户的方法；
        SecurityUser securityUser = dbUserDetailService.loadUserByUsername(userName);
        if (securityUser == null) {
            throw new BadCredentialsException("用户名不存在");
        }

        // 这里我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, securityUser.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 这里还可以加一些其他信息的判断，比如用户账号已停用等判断。

        Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(securityUser, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}

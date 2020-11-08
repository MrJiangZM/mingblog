package com.ming.blog.config.security.handler;

import com.ming.blog.config.security.DBUserDetailService;
import com.ming.blog.config.security.SecurityUser;
import com.ming.blog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 3:03 下午
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DBUserDetailService dbUserDetailService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication)
            throws IOException, ServletException {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
            throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//        Collection<GrantedAuthority> authorities = securityUser.getAuthorities();
        String token = jwtUtil.createJWT(securityUser.getId() + "", securityUser.getUsername(), null);
        httpServletResponse.addHeader("token", token);
    }
}

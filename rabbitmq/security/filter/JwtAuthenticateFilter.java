package com.ming.blog.config.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 11:59 上午
 */
@Slf4j
public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        return this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        (User)
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
}

package com.ming.blog.filterconfig;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

public class TestFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter11111 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter11111 doFilter");
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter11111 destroy");
    }
}

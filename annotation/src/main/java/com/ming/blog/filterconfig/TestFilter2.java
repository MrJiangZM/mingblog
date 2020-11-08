package com.ming.blog.filterconfig;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter2 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter22222 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter22222 doFilter");
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter22222 destroy");
    }
}

package com.liukai.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author：liukai
 * @Date：2023/8/25 16:51
 */
//@WebFilter("*.do") 通配符
@WebFilter("*.do")
public class Filter03 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("===> C .....");
        // 放行
        chain.doFilter(request,response);

        System.out.println("===> C2 .....");
    }

    @Override
    public void destroy() {

    }
}

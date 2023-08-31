package com.liukai.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author：liukai
 * @Date：2023/8/25 16:51
 */
//@WebFilter("*.do") 通配符
//@WebFilter("/demo01.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("===> filter before do .....");
        // 放行
        chain.doFilter(request,response);

        System.out.println("===> filter after do .....");
    }

    @Override
    public void destroy() {

    }
}

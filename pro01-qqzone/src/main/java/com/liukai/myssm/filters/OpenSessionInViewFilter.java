package com.liukai.myssm.filters;

import com.liukai.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.sql.SQLException;

/**
 * @Author：liukai
 * @Date：2023/8/28 15:36
 */
@WebFilter("/fruit.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  {

        try {
            System.out.println(" 开启事务.... ");
            TransactionManager.beginTrans();
            chain.doFilter(request,response);
            System.out.println(" 提交事务.... ");
            TransactionManager.commit();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                System.out.println(" 回滚事务.... ");
                TransactionManager.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {

    }
}

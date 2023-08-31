package com.liukai.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author：liukai
 * @Date：2023/8/28 18:21
 */
@WebListener
public class MyServletConTextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Servlet 上下文对象初始化动作被我监听到了。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet 上下文对象销毁动作被我监听到了。。。");

    }
}

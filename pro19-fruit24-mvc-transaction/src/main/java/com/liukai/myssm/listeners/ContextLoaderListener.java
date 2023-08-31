package com.liukai.myssm.listeners;

import com.liukai.myssm.servlets.ioc.BeanFactory;
import com.liukai.myssm.servlets.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author：liukai
 * @Date：2023/8/28 18:44
 *
 * 监听上下文启动，在上下文启动的时候去创建IOC容器，然后将其保存到 servletContext 作用域中
 * 后面中央控制器 DispatcherServlet 再从 servletContext 中获取
 *
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // 1、获取 ServletContext 对象
        ServletContext servletContext = sce.getServletContext();
        // 2、获取上下文初始化参数
        String path = servletContext.getInitParameter("contextConfigLocation");
        // 3、创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        // 4、将IOC容器保存到 servletContext 上下文中
        servletContext.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

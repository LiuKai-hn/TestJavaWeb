package com.liukai.myssm.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：liukai
 * @Date：2023/8/24 14:07
 */
/*@WebServlet(
        urlPatterns = "/demo_01",
        initParams = {
                @WebInitParam(name="liu",value = "kai"),
                @WebInitParam(name="zhao",value = "yx")
        }

)*/
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        String initParameter = servletConfig.getInitParameter("jim");
        System.out.println("initParameter: " + initParameter);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        System.out.println("contextConfigLocation：" + contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}

// servlet 生命周期：实例化、初始化、服务、销毁
// 1、init()
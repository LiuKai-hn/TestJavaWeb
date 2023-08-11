package com.liukai.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//演示向HttpSession保存数据
// 先访问 demo04 然后再访问demo05,就能输出 uname的值，然后再换一个浏览器，直接访问demo05，返现uname 为 null
// 说明 只有相同的session才能访问相关属性
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("uname","lina");
    }
}

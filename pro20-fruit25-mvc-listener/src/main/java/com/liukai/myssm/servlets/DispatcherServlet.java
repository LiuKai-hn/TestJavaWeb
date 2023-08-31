package com.liukai.myssm.servlets;

import com.liukai.myssm.servlets.ioc.BeanFactory;
import com.liukai.myssm.servlets.ioc.ClassPathXmlApplicationContext;
import com.liukai.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author：liukai
 * @Date：2023/8/17 17:47
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet(){
    }

    // servlet 原始的 init() 方法 ，主要作用是它产生的@WebServlet
    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        //request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do") ;
        servletPath = servletPath.substring(0,lastDotIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index" ;
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())){
                    // 1、统一获取请求参数
                    // 获取当前方法的参数数组
                    Parameter[] parameters = method.getParameters();
                    // 存储当前方法的参数(因为参数类型不定，采用Object方式进行存储)
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i=0;i<parameters.length;i++) {
                        Parameter parameter = parameters[i];
                        String paramterName = parameter.getName();
                        if(paramterName.equals("request")){
                            parameterValues[i] = request;
                        }else if(paramterName.equals("response")){
                            parameterValues[i] = response;
                        }else if(paramterName.equals("session")){
                            parameterValues[i] = request.getSession();
                        }else {// 从请求中获取参数值
                            String parameterValue = request.getParameter(paramterName);
                            // 获取参数类型
                            String parameterTypeName = parameter.getType().getName();
                            // 如果不转，则会报参数类型不匹配异常
                            if(null != parameterValue && "java.lang.Integer".equals(parameterTypeName)){
                                parameterValues[i] = Integer.parseInt(parameterValue);
                            }else {
                                parameterValues[i]=parameterValue;
                            }
                        }
                    }
                    // 2、Controller 中的方法调用
                    method.setAccessible(true);
                    Object methodReturnObj = method.invoke(controllerBeanObj,parameterValues);
                    String methodReturnStr =(String)methodReturnObj;

                    // 3、视图处理
                    if (methodReturnStr.startsWith("redirect:")){ // eg: redirct:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,request,response); // eg: error
                    }
                }
            }

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

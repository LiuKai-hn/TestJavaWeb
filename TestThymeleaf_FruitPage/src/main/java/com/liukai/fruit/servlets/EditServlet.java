package com.liukai.fruit.servlets;

import com.liukai.fruit.dao.FruitDAO;
import com.liukai.fruit.dao.impl.FruitDAOImpl;
import com.liukai.fruit.dao.pojo.Fruit;
import com.liukai.myssm.servlets.ViewBaseServlet;
import com.liukai.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：liukai
 * @Date：2023/8/17 10:27
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            Fruit fruit = fruitDAO.getFruitByFid(Integer.parseInt(fidStr));
            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));

        //4.资源跳转
        //super.processTemplate("index",request,response);
        //request.getRequestDispatcher("index.html").forward(request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");
    }
}

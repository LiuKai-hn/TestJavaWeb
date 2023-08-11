package com.liukai.servlets;

import com.liukai.fruit.dao.impl.FruitDAOImpl;
import com.liukai.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        FruitDAOImpl fruitDAO = new FruitDAOImpl();

        boolean isadded = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(isadded?"添加成功！":"添加失败！");

    }
}

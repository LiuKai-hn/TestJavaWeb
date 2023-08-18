package com.liukai.fruit.servlets;

import com.liukai.fruit.dao.FruitDAO;
import com.liukai.fruit.dao.impl.FruitDAOImpl;
import com.liukai.myssm.servlets.ViewBaseServlet;
import com.liukai.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：liukai
 * @Date：2023/8/17 9:56
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            //super.processTemplate("index",request,response);
            response.sendRedirect("index");
        }

    }
}

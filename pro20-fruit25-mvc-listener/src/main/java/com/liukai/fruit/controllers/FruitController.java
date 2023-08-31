package com.liukai.fruit.controllers;

import com.liukai.fruit.dao.pojo.Fruit;
import com.liukai.fruit.service.FruitService;
import com.liukai.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/17 19:01
 */
public class FruitController{

    private FruitService fruitService = null;

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark)  {
        //3.执行更新
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        return "redirect:fruit.do";
    }

    private String edit(Integer fid,HttpServletRequest request) {
        if (null!=fid) {
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid) {
        if (null!=fid) {
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitService.addFruit(fruit);

        return "redirect:fruit.do";

    }

    private String index(String oper, Integer pageNo, String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(null ==pageNo){
            pageNo = 1;
        }
        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1;
            //如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //将keyword保存（覆盖）到session中
            session.setAttribute("keyword", keyword);
        } else {
            //如果不是点击的查询按钮，那么查询是基于session中保存的现有keyword进行查询
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNo", pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList", fruitList);

        Integer pageCount = fruitService.getPageCount(keyword);

        session.setAttribute("pageCount", pageCount);

        return "index";
    }
}
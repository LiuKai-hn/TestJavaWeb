package com.liukai.fruit.service.impl;

import com.liukai.fruit.dao.FruitDAO;
import com.liukai.fruit.dao.pojo.Fruit;
import com.liukai.fruit.service.FruitService;
import com.liukai.util.ConnUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/24 14:43
 *
 *
 * 故意演示 错误，来模拟事务
 */
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        Connection getconn = ConnUtil.getconn();
        System.out.println("getFruitList = " + getconn);
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
        Fruit fruitByFid = fruitDAO.getFruitByFid(3);
        fruitDAO.updateFruit(fruitByFid);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {

        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {

        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        Connection getconn = ConnUtil.getconn();
        System.out.println("getFruitList = " + getconn);
        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}

package com.liukai.fruit.service;

import com.liukai.fruit.dao.pojo.Fruit;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/24 14:38
 */
public interface FruitService {

    // 获取指定页面序列的信息
    List<Fruit> getFruitList(String keyword, Integer pageNo);
    //添加
    void addFruit(Fruit fruit);
    //根据id查看指定库存
    Fruit getFruitByFid(Integer fid);
    //删除指定id库存
    void delFruit(Integer fid);
    //查询总页数
    Integer getPageCount(String keyword);
    //修改库存
    void updateFruit(Fruit fruit);

}

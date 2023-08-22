package com.liukai.fruit.dao;

import com.liukai.fruit.dao.pojo.Fruit;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/15 16:02
 */
public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList(String keywords, Integer pageNo);

    //新增库存
    boolean addFruit(Fruit fruit);
    //修改库存
    void updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //根据fid查询特定库存
    Fruit getFruitByFid(Integer fid);

    //删除特定库存记录
    void delFruit(Integer fid);

    int getFruitCount(String keywords);

}

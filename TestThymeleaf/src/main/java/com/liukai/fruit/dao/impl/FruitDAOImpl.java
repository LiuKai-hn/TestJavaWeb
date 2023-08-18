package com.liukai.fruit.dao.impl;

import com.liukai.fruit.dao.FruitDAO;
import com.liukai.fruit.dao.base.BaseDAO;
import com.liukai.fruit.dao.pojo.Fruit;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/15 16:01
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        return super.executeUpdate("insert into t_fruit values(0,?,?,?,?)",fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark())>0;
    }
    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? " ;
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ",fname);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ? ",fid);
    }

    @Override
    public void delFruit(Integer fid) {
        String sql = "delete from t_fruit where fid= ? " ;
        super.executeUpdate(sql,fid);
    }
}

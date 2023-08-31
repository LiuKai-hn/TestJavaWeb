package com.liukai.qqzone.dao.impl;

import com.liukai.qqzone.dao.UserBasicDAO;
import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 10:03
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBaisc(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?" , loginId, pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select t2.* from t_friend t1 join t_user_basic t2 on t1.fid=t2.id where t1.uid= ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer uid) {
        return super.load("select * from t_user_basic where id = ?" , uid);
    }
}

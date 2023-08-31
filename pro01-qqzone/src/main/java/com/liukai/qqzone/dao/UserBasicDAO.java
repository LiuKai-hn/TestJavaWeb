package com.liukai.qqzone.dao;

import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 9:55
 */
public interface UserBasicDAO {

    // 根据账号和密码获取特定用户
    UserBasic getUserBaisc(String loginId, String pwd);

    // 获取指定用户的所有好友
    List<UserBasic> getUserBasicList(UserBasic userBasic);

    UserBasic getUserBasicById(Integer uid);



}

package com.liukai.qqzone.service;

import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 10:22
 */
public interface UserBasicService {

    UserBasic login(String loginId,String pwd);
    List<UserBasic> getFriendList(UserBasic userBasic);
    UserBasic getUserBasicByid(Integer uid);
}

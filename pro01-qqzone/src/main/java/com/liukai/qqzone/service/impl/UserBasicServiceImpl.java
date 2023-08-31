package com.liukai.qqzone.service.impl;

import com.liukai.qqzone.dao.UserBasicDAO;
import com.liukai.qqzone.pojo.UserBasic;
import com.liukai.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 10:24
 */
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO;

    @Override
    public UserBasic login(String loginId, String pwd) {
        // 获取 特定的 用户
        UserBasic userBaisc = userBasicDAO.getUserBaisc(loginId, pwd);
       /* // 获取 特定用户的好友
        List<UserBasic> friendList = getFriendList(userBaisc);
        // 给特定用户配置好友列表
        userBaisc.setFriendList(friendList);
*/

        return userBaisc;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {

        List<UserBasic> friendBasicList = userBasicDAO.getUserBasicList(userBasic);
        return friendBasicList;
    }

    @Override
    public UserBasic getUserBasicByid(Integer uid) {

        return userBasicDAO.getUserBasicById(uid);
    }
}

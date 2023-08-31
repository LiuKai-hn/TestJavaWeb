package com.liukai.qqzone.controller;

import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.pojo.UserBasic;
import com.liukai.qqzone.service.TopicService;
import com.liukai.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 11:09
 */
public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId,String pwd, HttpSession session){

        UserBasic userBasic = userBasicService.login(loginId, pwd);

        if (userBasic!=null){

            // 获取好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            // 获取日志列表
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            /**
             * 这里的 userBasic 和 friend 主要用于判断是否进入自己的空间或别人的空间
             */
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);

            return "index";
        }else {
            return "login";
        }


    }

    public String friend(Integer uid, HttpSession session){
        // 1、根据id获取指定用户的信息
        UserBasic currentFriend = userBasicService.getUserBasicByid(uid);
        // 2、获取指定朋友的 topicList
        List<Topic> topicList = topicService.getTopicList(currentFriend);
        currentFriend.setTopicList(topicList);

        session.setAttribute("friend",currentFriend);

        return "index";

    }
}

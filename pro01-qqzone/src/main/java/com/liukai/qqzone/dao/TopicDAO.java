package com.liukai.qqzone.dao;

import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 9:58
 */
public interface TopicDAO {


    // 获取特定用户的所有日志
    List<Topic> getTopicList(UserBasic userBasic);

    // 添加日志
    void addTopic(Topic topic);

    // 删除日志
    void delTopic(Topic topic);

    // 获取特定日志信息
    Topic getTopic(Integer tid);
}

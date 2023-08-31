package com.liukai.qqzone.service;

import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 11:02
 */
public interface TopicService {

    // 查询特定用户的日志状态
    List<Topic> getTopicList(UserBasic userBasic);
    Topic getTopicById(Integer tid);
}

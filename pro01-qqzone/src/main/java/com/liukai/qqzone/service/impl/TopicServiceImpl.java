package com.liukai.qqzone.service.impl;

import com.liukai.qqzone.dao.TopicDAO;
import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.pojo.UserBasic;
import com.liukai.qqzone.service.TopicService;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 11:03
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {

        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer tid) {
        return topicDAO.getTopic(tid);
    }
}

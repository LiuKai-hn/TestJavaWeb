package com.liukai.qqzone.dao.impl;

import com.liukai.qqzone.dao.TopicDAO;
import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 10:20
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?",userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer tid) {
        return super.load("select * from t_topic where id = ?",tid);
    }
}

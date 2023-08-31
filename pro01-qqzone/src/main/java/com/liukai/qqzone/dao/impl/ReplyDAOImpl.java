package com.liukai.qqzone.dao.impl;

import com.liukai.qqzone.dao.ReplyDAO;
import com.liukai.qqzone.pojo.Reply;
import com.liukai.qqzone.pojo.Topic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/31 11:30
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return super.executeQuery("select * from t_topic where id = ? ", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {

    }

    @Override
    public void delReply(Reply reply) {

    }
}

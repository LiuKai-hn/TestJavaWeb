package com.liukai.qqzone.service.impl;

import com.liukai.qqzone.dao.ReplyDAO;
import com.liukai.qqzone.pojo.Reply;
import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.service.ReplyService;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/31 11:21
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;

    @Override
    public List<Reply> getReplyListByTopicId(Topic topic) {
        return replyDAO.getReplyList(topic);
    }
}

package com.liukai.qqzone.service;

import com.liukai.qqzone.pojo.Reply;
import com.liukai.qqzone.pojo.Topic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/31 11:18
 */
public interface ReplyService {

    // 根据 topic id 获取所有关联的回复
    List<Reply> getReplyListByTopicId(Topic topic);
}

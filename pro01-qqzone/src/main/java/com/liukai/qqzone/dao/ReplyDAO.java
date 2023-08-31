package com.liukai.qqzone.dao;

import com.liukai.qqzone.pojo.Reply;
import com.liukai.qqzone.pojo.Topic;

import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/30 10:00
 */
public interface ReplyDAO {

    // 获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);

    // 添加回复
    void addReply(Reply reply);
    // 删除回复
    void delReply(Reply reply);

}

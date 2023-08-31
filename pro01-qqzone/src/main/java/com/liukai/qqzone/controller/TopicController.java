package com.liukai.qqzone.controller;

import com.liukai.qqzone.pojo.Reply;
import com.liukai.qqzone.pojo.Topic;
import com.liukai.qqzone.service.ReplyService;
import com.liukai.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author：liukai
 * @Date：2023/8/31 10:15
 */
public class TopicController {

    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session){
        // 根据 id 获取 特定的 topic
        Topic topic = topicService.getTopicById(id);

        List<Reply> replyListByTopicId = replyService.getReplyListByTopicId(topic);

        topic.setReplyList(replyListByTopicId);

        session.setAttribute("topic",topic);

        return "frames/detail";

    }
}

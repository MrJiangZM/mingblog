package com.blog.mq.controller;

import com.blog.mq.direct.DirectQueueConfig;
import com.blog.mq.entity.MessageEntity;
import com.blog.mq.fanout.FanoutQueueConfig;
import com.blog.mq.sender.RabbitmqSender;
import com.blog.mq.topic.TopicQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test2")
public class TestController {

    @Autowired
    private RabbitmqSender rabbitmqSender;


    @GetMapping("/topic/test1")
    public void test2(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendTopicMsg(TopicQueueConfig.EXCHANGE_A, TopicQueueConfig.ROUTING_KEY_TOPIC_A, build);
        }
    }

    @GetMapping("/topic/test2")
    public void test3(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendTopicMsg(TopicQueueConfig.EXCHANGE_A, TopicQueueConfig.ROUTING_KEY_TOPIC_B, build);
        }
    }

    @GetMapping("/topic/test3")
    public void test4(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendTopicMsg(TopicQueueConfig.EXCHANGE_A, TopicQueueConfig.ROUTING_KEY_TOPIC_C, build);
        }
    }

    /**
     * 直接往队列中丢消息
     */
    @GetMapping("/p2p/test1")
    public void test1(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendP2P("hello", build);
        }
    }

    @GetMapping("/direct/test1")
    public void test5(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendTopicMsg(DirectQueueConfig.DIRECT_EXCHANGE_A,
                    DirectQueueConfig.ROUTING_KEY_TOPIC_A, build);
        }
    }

    @GetMapping("/fanout/test1")
    public void test6(){
        for (int i = 0; i < 20; i++) {
            MessageEntity build = MessageEntity.builder().id(i).build();
            rabbitmqSender.sendTopicMsg(FanoutQueueConfig.FANOUT_EXCHANGE_A, "any key", build);
        }
    }

}

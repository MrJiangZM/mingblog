package com.blog.mq.p2p;

import com.alibaba.fastjson.JSONObject;
import com.blog.mq.entity.MessageEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class P2PQueueListener {

    @RabbitListener(queues = "hello")
    public void directQueueListener1(Message message, Channel channel) {
        log.info("intSendAndListenTest: get message");
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "11111111");
    }

    @RabbitListener(queues = "hello")
    public void directQueueListener2(Message message, Channel channel) {
        log.info("intSendAndListenTest: get message");
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "2222222");
    }

}

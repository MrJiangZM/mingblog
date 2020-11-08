package com.blog.mq.direct;

import com.alibaba.fastjson.JSONObject;
import com.blog.mq.entity.MessageEntity;
import com.blog.mq.topic.TopicQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DirectQueueListener {

    @RabbitListener(queues = {DirectQueueConfig.QUEUE_TOPIC_A})
    public void topicListener1(Message message, Channel channel) throws IOException {
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "=====direct_QUEUE_TOPIC_A======" + message.getMessageProperties().getReceivedRoutingKey());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

}

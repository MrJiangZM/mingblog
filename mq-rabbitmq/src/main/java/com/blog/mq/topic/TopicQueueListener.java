package com.blog.mq.topic;

import com.alibaba.fastjson.JSONObject;
import com.blog.mq.entity.MessageEntity;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TopicQueueListener {

    @RabbitListener(queues = {TopicQueueConfig.QUEUE_TOPIC_A, TopicQueueConfig.QUEUE_TOPIC_B})
    public void topicListener1(Message message, Channel channel) throws IOException {
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "=====All======" + message.getMessageProperties().getReceivedRoutingKey());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = {TopicQueueConfig.QUEUE_TOPIC_A})
    public void topicListener2(Message message, Channel channel) throws IOException {
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "====AAA=======" + message.getMessageProperties().getReceivedRoutingKey());
        // 消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = {TopicQueueConfig.QUEUE_TOPIC_B})
    public void topicListener3(Message message, Channel channel) throws IOException {
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "====BBB=======" + message.getMessageProperties().getReceivedRoutingKey());
        // 消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = {TopicQueueConfig.QUEUE_TOPIC_C})
    public void topicListener4(Message message, Channel channel) throws IOException {
        MessageEntity messageEntity = JSONObject.parseObject(message.getBody(), MessageEntity.class);
        System.out.println(messageEntity + "====CCC=======" + message.getMessageProperties().getReceivedRoutingKey());
        // 消费成功
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }



}

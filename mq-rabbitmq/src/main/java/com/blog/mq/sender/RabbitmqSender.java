package com.blog.mq.sender;

import com.blog.mq.topic.TopicQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class RabbitmqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送到 指定队列
     * @param queueName
     * @param obj      发送内容
     */
    public void sendP2P(String queueName, Object obj) {
        System.out.println("=============================发送");
        CorrelationData correlationData =
                new CorrelationData(UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY));
        log.info("send direct,correlation id:[{}],queue name:[{}],date:[{}]",
                correlationData.getId(), queueName, new Date());
        this.rabbitTemplate.convertAndSend(queueName, obj, correlationData);
    }

    /**
     * topic 发布订阅模式  发送消息到指定交换机以及routeKey 找到对应的队列
     * @param exchangeName
     * @param routeKey
     * @param obj
     */
    public void sendTopicMsg(String exchangeName, String routeKey, Object obj) {
        CorrelationData correlationData =
                new CorrelationData(UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY));
        this.rabbitTemplate.convertAndSend(exchangeName, routeKey, obj, correlationData);
    }

}

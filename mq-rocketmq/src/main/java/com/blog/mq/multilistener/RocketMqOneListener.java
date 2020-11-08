package com.blog.mq.multilistener;

import com.blog.mq.listener.RocketMqTopicEnum;
import com.blog.mq.listener.base.BaseRocketMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 多个监听者的测试
 */
@Slf4j
@Configuration
public class RocketMqOneListener implements MessageListenerConcurrently {

    @Resource(name = "rocketMqConsumerMap")
    private Map<String, BaseRocketMqConsumer> rocketMqConsumerMap;

    public BaseRocketMqConsumer getMessageConsumer(@NonNull MessageExt messageExt) {
        String topic = messageExt.getTopic();
        Assert.isTrue(StringUtils.isNotEmpty(topic), "消息体没有对应的topic");
        BaseRocketMqConsumer consumer = rocketMqConsumerMap.get(topic);
        Assert.notNull(consumer, "找不到对应的consumer");
        return consumer;
    }

    public boolean retry(@NonNull MessageExt messageExt) {
        RocketMqTopicEnum rocketMqTopicEnum = RocketMqTopicEnum.valueOf(messageExt.getTopic());
        return messageExt.getReconsumeTimes() < rocketMqTopicEnum.getRetryNum();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("listener 111111  接受到的消息为："+new String(messageExt.getBody()));
        BaseRocketMqConsumer messageConsumer = getMessageConsumer(messageExt);
        if (!retry(messageExt)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        return messageConsumer.consumeMessage(messageExt);
    }
}

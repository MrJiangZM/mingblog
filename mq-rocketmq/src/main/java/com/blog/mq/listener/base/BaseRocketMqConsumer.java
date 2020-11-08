package com.blog.mq.listener.base;

import com.blog.mq.config.CannotFindConsumerException;
import com.blog.mq.listener.BookRocketMqConsumer;
import com.blog.mq.listener.MoneyRocketMqConsumer;
import com.blog.mq.listener.OrderRocketMqConsumer;
import com.blog.mq.listener.RocketMqTopicEnum;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class BaseRocketMqConsumer {

    @Bean("rocketMqConsumerMap")
    public Map<String, BaseRocketMqConsumer> getRocketMqConsumerMap(OrderRocketMqConsumer orderRocketMqConsumer,
                                                                    BookRocketMqConsumer bookRocketMqConsumer,
                                                                    MoneyRocketMqConsumer moneyRocketMqConsumer) {
        return ImmutableMap.<String, BaseRocketMqConsumer>builder()
                .put(RocketMqTopicEnum.ORDER_TOPIC.name(), orderRocketMqConsumer)
                .put(RocketMqTopicEnum.BOOK_TOPIC.name(), bookRocketMqConsumer)
                .put(RocketMqTopicEnum.MONEY_TOPIC.name(), moneyRocketMqConsumer)
                .build();
    }

    public abstract String getTopic();

    public abstract ConsumeConcurrentlyStatus consumeMessage(MessageExt messageExt);

}

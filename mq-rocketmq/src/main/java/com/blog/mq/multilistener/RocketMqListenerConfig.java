package com.blog.mq.multilistener;

import com.blog.mq.listener.base.BaseRocketMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Configuration
public class RocketMqListenerConfig  {

    private String TOPICS_ONE = "BOOK_TOPIC~*;ORDER_TOPIC~*;";
    private String TOPICS_TWO = "BOOK_TOPIC~*;MONEY_TOPIC~*;";



    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

    @Resource
    private RocketMqOneListener rocketMqOneListener;
    @Resource
    private RocketMqTwoListener rocketMqTwoListener;

    @Bean("defaultMQPushConsumerOne")
    public DefaultMQPushConsumer getRocketMQConsumer1(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("multiListener___one");
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        // 这里只能注册一个listener，重复注册会导致覆盖
        consumer.registerMessageListener(rocketMqTwoListener);
        consumer.registerMessageListener(rocketMqOneListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            String[] topicTagsArr = TOPICS_ONE.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }

//    @Bean("defaultMQPushConsumerTwo")
    public DefaultMQPushConsumer getRocketMQConsumer2(){
        // 相当于新建两个组，两个组不互通，相同的消息会发送到两个组中
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("multiListener___two");
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(rocketMqTwoListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            String[] topicTagsArr = TOPICS_TWO.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }

}

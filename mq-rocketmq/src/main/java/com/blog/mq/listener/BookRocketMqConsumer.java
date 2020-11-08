package com.blog.mq.listener;

import com.blog.mq.listener.base.BaseRocketMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BookRocketMqConsumer extends BaseRocketMqConsumer {

    @Override
    public String getTopic() {
        return RocketMqTopicEnum.BOOK_TOPIC.name();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(MessageExt messageExt) {
        String tags = messageExt.getTags() ;
        switch (tags){
            case "book_tag1":
                // 可以对应不同的业务逻辑
                log.info("book tag11111 =============== >>"+tags);
                break ;
            case "book_tag2":
                // 可以对应不同的业务逻辑
                log.info("book tag22222 =============== >>"+tags);
                break ;
            default:
                log.info("未匹配到 book TagUUUUU == >>"+tags);
                break;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}

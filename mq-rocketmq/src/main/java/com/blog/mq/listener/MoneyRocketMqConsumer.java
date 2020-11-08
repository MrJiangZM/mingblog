package com.blog.mq.listener;

import com.blog.mq.listener.base.BaseRocketMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoneyRocketMqConsumer extends BaseRocketMqConsumer {

    @Override
    public String getTopic() {
        return RocketMqTopicEnum.MONEY_TOPIC.name();
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(MessageExt messageExt) {
        String tags = messageExt.getTags() ;
        switch (tags){
            case "money_tag1":
                // 可以对应不同的业务逻辑
                log.info("money tag11111 =============== >>"+tags);
                break ;
            case "money_tag2":
                // 可以对应不同的业务逻辑
                log.info("money tag22222 =============== >>"+tags);
                break ;
            default:
                log.info("未匹配到 money TagUUUUU == >>"+tags);
                break;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}

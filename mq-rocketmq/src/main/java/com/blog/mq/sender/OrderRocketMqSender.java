package com.blog.mq.sender;

import com.blog.mq.sender.base.BaseRocketMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRocketMqSender extends BaseRocketMqSender {

    private static final String ORDER_TOPIC = "ORDER_TOPIC";

    @Override
    protected String getTopic() {
        return ORDER_TOPIC;
    }

}

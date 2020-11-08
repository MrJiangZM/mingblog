package com.blog.mq.sender;

import com.blog.mq.sender.base.BaseRocketMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookRocketMqSender extends BaseRocketMqSender {

    private static final String BOOK_TOPIC = "BOOK_TOPIC";

    @Override
    protected String getTopic() {
        return BOOK_TOPIC;
    }
}

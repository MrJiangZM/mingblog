package com.blog.mq.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RocketMqTopicEnum {

    ORDER_TOPIC("ORDER_TOPIC", 3, "订单相关topic"),
    BOOK_TOPIC("BOOK_TOPIC", 2, "书籍相关topic"),
    MONEY_TOPIC("MONEY_TOPIC", 2, "钱币相关topic"),
    ;

    private String code;
    private Integer retryNum;
    private String msg;
}

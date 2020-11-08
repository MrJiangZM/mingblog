package com.blog.mq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DirectQueueConfig {


    public static final String DIRECT_EXCHANGE_A = "DIRECT_EXCHANGE_A";

    // 消息队列
    public static final String QUEUE_TOPIC_A = "direct.messages1";
    public static final String QUEUE_TOPIC_B = "direct.messages2";
    public static final String QUEUE_TOPIC_C = "direct.messages3";

    // 路由(topic规则)
    public static final String ROUTING_KEY_TOPIC_A = "direct.messages1"; //精确匹配
    public static final String ROUTING_KEY_TOPIC_B = "direct.messages2"; //通配匹配
    public static final String ROUTING_KEY_TOPIC_C = "direct.messages3"; //精确匹配

    @Bean(DIRECT_EXCHANGE_A)
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_A);
    }

    /**
     * 所有的消息转发类型都需要队列，队列对应不同的消费者
     * @return
     */
    @Bean(QUEUE_TOPIC_A)
    public Queue queueMessageA() {
        return new Queue(QUEUE_TOPIC_A, true);
    }

    /**
     * 消息队列A(topic.messge)绑定到交换机上, 路由规则是topic.message
     * 一个交换机可以绑定多个消息队列，消息通过一个交换机，可以分发到不同的队列当中去。
     * @return
     */
    @Bean("directQueueMsg")
    Binding bindingExchangeMessage1(@Qualifier(QUEUE_TOPIC_A) Queue queueMessage,
                                    @Qualifier(DIRECT_EXCHANGE_A) DirectExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_KEY_TOPIC_A);
    }

}

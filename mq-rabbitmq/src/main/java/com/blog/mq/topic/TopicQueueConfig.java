package com.blog.mq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 遇到的问题：
 *
 * rabbitmq 交换机对应不同   key    不同的key挂不同的queue 但是queue的消费者消费原则需要统一的梳理下，
 *
 * 往   A    发会发现    监听1 收到20个 监听2 10个    监听3 10个
 * 往   B    发会发现    监听1  收到10个 监听3 收到10个
 * 往   C    发会发现    监听1 10个 监听3 10个 监听4 20个
 *
 * 这种消息分发大坑
 * 所以推荐一个topic对应一个消费者，不推荐routeKey通配匹配
 */
@Slf4j
@Configuration
public class TopicQueueConfig {

    public static final String EXCHANGE_A = "EXCHANGE_A";
    public static final String EXCHANGE_B = "EXCHANGE_B";

    // 消息队列
    public static final String QUEUE_TOPIC_A = "topic.message1";
    public static final String QUEUE_TOPIC_B = "topic.messages2";
    public static final String QUEUE_TOPIC_C = "topic.messages3";

    // 路由(topic规则)
    public static final String ROUTING_KEY_TOPIC_A = "topic.message1"; //精确匹配
    public static final String ROUTING_KEY_TOPIC_B = "topic.#"; //通配匹配
    public static final String ROUTING_KEY_TOPIC_C = "topic.message2"; //精确匹配

    /**
     * 设置交换机类型
     FanoutExchange: 广播，将消息分发到所有的绑定队列
     HeadersExchange ：通过添加键值对key-value匹配
     DirectExchange:按照路由Routingkey分发指定队列
     TopicExchange:topic主题模式，多关键字匹配
     */
    @Bean(EXCHANGE_A)
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_A);
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
     * 所有的消息转发类型都需要队列，队列对应不同的消费者
     * @return
     */
    @Bean(QUEUE_TOPIC_B)
    public Queue queueMessageB() {
        return new Queue(QUEUE_TOPIC_B, true);
    }
    /**
     * 所有的消息转发类型都需要队列，队列对应不同的消费者
     * @return
     */
    @Bean(QUEUE_TOPIC_C)
    public Queue queueMessageC() {
        return new Queue(QUEUE_TOPIC_C, true);
    }

    /**
     * 消息队列A(topic.messge)绑定到交换机上, 路由规则是topic.message
     * 一个交换机可以绑定多个消息队列，消息通过一个交换机，可以分发到不同的队列当中去。
     * @return
     */
    @Bean
    Binding bindingExchangeMessage1(@Qualifier(QUEUE_TOPIC_A) Queue queueMessage,
                                    @Qualifier(EXCHANGE_A) TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_KEY_TOPIC_A);
    }

    /**
     * 消息队列A(topic.messge)绑定到交换机上, 路由规则是topic.message
     * 一个交换机可以绑定多个消息队列，消息通过一个交换机，可以分发到不同的队列当中去。
     * @return
     */
    @Bean
    Binding bindingExchangeMessage2(@Qualifier(QUEUE_TOPIC_B) Queue queueMessage,
                                   @Qualifier(EXCHANGE_A) TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_KEY_TOPIC_B);
    }
    /**
     * 消息队列A(topic.messge)绑定到交换机上, 路由规则是topic.message
     * 一个交换机可以绑定多个消息队列，消息通过一个交换机，可以分发到不同的队列当中去。
     * @return
     */
    @Bean
    Binding bindingExchangeMessage3(@Qualifier(QUEUE_TOPIC_C) Queue queueMessage,
                                    @Qualifier(EXCHANGE_A) TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_KEY_TOPIC_C);
    }
}

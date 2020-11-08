package com.blog.mq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FanoutQueueConfig {

    public static final String FANOUT_EXCHANGE_A = "FANOUT_EXCHANGE_A";

    public static final String QUEUE_TOPIC_A = "fanout.message1";
    public static final String QUEUE_TOPIC_B = "fanout.message2";
    public static final String QUEUE_TOPIC_C = "fanout.message3";
    public static final String QUEUE_TOPIC_D = "fanout.message4";

    @Bean(FANOUT_EXCHANGE_A)
    public FanoutExchange exchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_A);
    }

    @Bean(QUEUE_TOPIC_A)
    public Queue fanoutQueue1() {
        return new Queue(QUEUE_TOPIC_A, true);
    }
    @Bean(QUEUE_TOPIC_B)
    public Queue fanoutQueue2() {
        return new Queue(QUEUE_TOPIC_B, true);
    }
    @Bean(QUEUE_TOPIC_C)
    public Queue fanoutQueue3() {
        return new Queue(QUEUE_TOPIC_C, false);
    }
    @Bean(QUEUE_TOPIC_D)
    public Queue fanoutQueue4() {
        return new Queue(QUEUE_TOPIC_D, false);
    }

    @Bean("FANOUT_BINDING_A")
    Binding bindingExchangeMessage1(@Qualifier(QUEUE_TOPIC_A) Queue queueMessage,
                                    @Qualifier(FANOUT_EXCHANGE_A) FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }
    @Bean("FANOUT_BINDING_B")
    Binding bindingExchangeMessage2(@Qualifier(QUEUE_TOPIC_B) Queue queueMessage,
                                    @Qualifier(FANOUT_EXCHANGE_A) FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }
    @Bean("FANOUT_BINDING_C")
    Binding bindingExchangeMessage3(@Qualifier(QUEUE_TOPIC_C) Queue queueMessage,
                                    @Qualifier(FANOUT_EXCHANGE_A) FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }
    @Bean("FANOUT_BINDING_D")
    Binding bindingExchangeMessage4(@Qualifier(QUEUE_TOPIC_D) Queue queueMessage,
                                    @Qualifier(FANOUT_EXCHANGE_A) FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }

}

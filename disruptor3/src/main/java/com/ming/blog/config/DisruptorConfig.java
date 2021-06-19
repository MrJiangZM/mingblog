package com.ming.blog.config;

import com.ming.blog.email.EmailDataEventHandler;
import com.ming.blog.order.OrderDataEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisruptorConfig {

    /**
     * smsParamEventHandler1
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler1() {
        return new OrderDataEventHandler();
    }

    /**
     * smsParamEventHandler2
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler2() {
        return new OrderDataEventHandler();
    }

    /**
     * smsParamEventHandler3
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler3() {
        return new OrderDataEventHandler();
    }


    /**
     * smsParamEventHandler4
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler4() {
        return new OrderDataEventHandler();
    }

    /**
     * smsParamEventHandler5
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler5() {
        return new OrderDataEventHandler();
    }


    /**
     * smsParamEventHandler5
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public OrderDataEventHandler smsParamEventHandler6() {
        return new OrderDataEventHandler();
    }


    @Bean
    public EmailDataEventHandler smsEmailParamEventHandler4() {
        return new EmailDataEventHandler();
    }

    /**
     * smsParamEventHandler5
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public EmailDataEventHandler smsEmailParamEventHandler5() {
        return new EmailDataEventHandler();
    }


    /**
     * smsParamEventHandler5
     *
     * @return OrderDataEventHandler
     */
    @Bean
    public EmailDataEventHandler smsEmailParamEventHandler6() {
        return new EmailDataEventHandler();
    }


}
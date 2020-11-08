package com.blog.mq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;

@Slf4j
@Component
public class FanoutQueueListener {

    @RabbitListener(queues = FanoutQueueConfig.QUEUE_TOPIC_A)
    public void test1(){
        System.out.println("--------AAAAAAAAAAA");
    }

    @RabbitListener(queues = FanoutQueueConfig.QUEUE_TOPIC_A)
    public void test2(){
        System.out.println("--------BBBBBBBBBB");

    }

    @RabbitListener(queues = FanoutQueueConfig.QUEUE_TOPIC_A)
    public void test3(){
        System.out.println("--------CCCCCCCCCCC");

    }

    @RabbitListener(queues = FanoutQueueConfig.QUEUE_TOPIC_A)
    public void test4(){
        System.out.println("--------DDDDDDDDDDDDD");
    }

}

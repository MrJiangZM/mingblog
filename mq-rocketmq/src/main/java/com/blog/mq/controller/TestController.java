package com.blog.mq.controller;

import com.blog.mq.entity.MessageEntity;
import com.blog.mq.entity.TestEntity;
import com.blog.mq.listener.RocketMqTopicEnum;
import com.blog.mq.service.RocketmqMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.bouncycastle.math.ec.ECCurve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rocket")
public class TestController {

    @Autowired
    private RocketmqMsgService rocketmqMsgService;

    @GetMapping("/test1")
    public void test1() throws InterruptedException, RemotingException,
            MQClientException, MQBrokerException, UnsupportedEncodingException {
        rocketmqMsgService.sendMsg("ha");
    }

    @GetMapping("/test2")
    public void test2(String topic, String tag, String msg) throws InterruptedException, RemotingException,
            MQClientException, MQBrokerException, UnsupportedEncodingException {
        rocketmqMsgService.sendOrderMsg1(topic, tag,  msg);
    }

    @GetMapping("/test3")
    public void test3() throws InterruptedException, RemotingException,
            MQClientException, MQBrokerException, UnsupportedEncodingException {
        rocketmqMsgService.sendOrderMsg("我也不知道是撒");
    }

    @GetMapping("/test4")
    public void test4(String topic, String tag, String msg) throws InterruptedException, RemotingException,
            MQClientException, MQBrokerException, UnsupportedEncodingException {
        for (int i = 0; i < 20; i++) {
            rocketmqMsgService.sendOrderMsg1(topic, tag,  msg+i);
        }
    }



}

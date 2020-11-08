package com.blog.mq.service.impl;

import com.blog.mq.sender.OrderRocketMqSender;
import com.blog.mq.service.RocketmqMsgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class RocketmqMsgServiceImpl implements RocketmqMsgService {

    @Autowired
    private OrderRocketMqSender orderRocketMqSender;

    @Override
    public void sendMsg(String msg) throws InterruptedException, RemotingException,
            UnsupportedEncodingException, MQClientException, MQBrokerException {
        SendResult sendResult = orderRocketMqSender.sendMsgToTopicAndTag("testTopic", "testTag", "msg");
        log.info("{}", sendResult);
    }

    @Override
    public void sendOrderMsg(String msg) throws InterruptedException, RemotingException,
            UnsupportedEncodingException, MQClientException, MQBrokerException {
        SendResult sendResult = orderRocketMqSender.sendMsgToTag("order_tag", msg);
        log.info("{}", sendResult);

    }

    @Override
    public void sendOrderMsg1(String topic, String tag, String msg) throws InterruptedException,
            RemotingException, UnsupportedEncodingException, MQClientException, MQBrokerException {
        orderRocketMqSender.sendMsgToTopicAndTag(topic, tag, msg);
    }
}

package com.blog.mq.sender.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;

@Slf4j
public abstract class BaseRocketMqSender {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    protected abstract String getTopic();

    public SendResult sendMsgToTopicAndTag(String topic, String tag, String body)
            throws InterruptedException, RemotingException, MQClientException,
            MQBrokerException, UnsupportedEncodingException {
        return sendMessage(new Message(topic, tag, body.getBytes(RemotingHelper.DEFAULT_CHARSET)));
    }

    public SendResult sendMsgToTag(String tags, String body)
            throws InterruptedException, RemotingException, MQClientException,
            MQBrokerException, UnsupportedEncodingException {
        String topic = getTopic();
        return sendMessage(new Message(topic, tags, body.getBytes(RemotingHelper.DEFAULT_CHARSET)));
    }

    public SendResult sendMessage(Message message) throws InterruptedException, RemotingException,
            MQClientException, MQBrokerException {
        StopWatch stop = new StopWatch();
        stop.start();
        SendResult result = defaultMQProducer.send(message);
        stop.stop();
        log.info("rocketmq 发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
        return result;
    }

}

package com.blog.mq.service;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public interface RocketmqMsgService {
    void sendMsg(String ha) throws InterruptedException, RemotingException, UnsupportedEncodingException, MQClientException, MQBrokerException;

    void sendOrderMsg(String message) throws InterruptedException, RemotingException, UnsupportedEncodingException, MQClientException, MQBrokerException;

    void sendOrderMsg1(String message, String tag, String msg) throws InterruptedException, RemotingException, UnsupportedEncodingException, MQClientException, MQBrokerException;

}

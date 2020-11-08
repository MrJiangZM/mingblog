package com.ming.blog.mq.event;


/**
 * EventChannel
 *
 * @author wuzbin
 * @date 16/5/28
 */
public interface EventChannel {
    void publish(Event event) throws Exception;

    <T> void publish(EventTypeEnum type, T param) throws Exception;

    Event consumer() throws Exception;

    void confirm(String processorName, String eventId) throws Exception;

    void processing(String processorName, Event event) throws Exception;
}
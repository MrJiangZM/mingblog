package com.ming.blog.mq.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class RedisEventChannel implements EventChannel {

    @Resource
    private RedisTemplate<String, Event> eventRedisTemplate;

    private String eventKey = "mingblog:events";
    // 处理中事件
    private String eventProcessingKey = "mingblog:events:processing";

    @Override
    public void publish(Event event) {
        publish(event, eventKey);
    }

    @Override
    public <T> void publish(EventTypeEnum eventType, T param) {
        Event<T> event = new Event<>(eventType.getEventType(), param);
        try {
            publish(event);
        } catch (Exception e) {
            log.error(String.format("发送事件异常.时间类型: %s, 事件参数: %s",
                    eventType.getDesc(), JSON.toJSONString(param)), e);
        }
    }

    private void publish(Event event, String key) {
        String eventId = UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY);
        event.setEventId(eventId);
        eventRedisTemplate.opsForList().leftPush(key, event);
    }

    @Override
    public Event consumer() {
        return eventRedisTemplate.opsForList().rightPop(eventKey);
    }

    @Override
    public void processing(String processorName, Event event) {
        eventRedisTemplate.opsForHash().put(eventProcessingKey, event.getEventId() + "-" + processorName, event);
    }

    @Override
    public void confirm(String processorName, String eventId) {
        if (StringUtils.isNotEmpty(eventId)) {
            eventRedisTemplate.opsForHash()
                    .delete(eventProcessingKey, eventId + "-" + processorName);
        }
    }

    public RedisTemplate<String, Event> getEventRedisTemplate() {
        return eventRedisTemplate;
    }

    public void setEventRedisTemplate(RedisTemplate<String, Event> eventRedisTemplate) {
        this.eventRedisTemplate = eventRedisTemplate;
    }

    public Map<String, Event> getProcessingEvent() {
        HashOperations<String, String, Event> hashOps = eventRedisTemplate.opsForHash();
        return hashOps.entries(eventProcessingKey);
    }

    public Map<Object, Object> listOfProcessingEvent() {
        return eventRedisTemplate.opsForHash().entries(eventProcessingKey);
    }

    public Long countOfProcessingEvent() {
        return eventRedisTemplate.opsForHash().size(eventProcessingKey);
    }

    public void deleteProcessingEvent(String hashKey) {
        eventRedisTemplate.opsForHash().delete(eventProcessingKey, hashKey);
    }

}

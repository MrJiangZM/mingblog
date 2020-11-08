package com.ming.blog.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class Event<T> implements Serializable {
    private int eventType;              //事件类型
    private T eventParams;              //事件参数类型
    String eventId;                     //事件id
    private long ts;                    //事件发生时间戳

    public Event() {
        this.ts = System.currentTimeMillis();
    }

    public Event(int eventType, T eventParams) {
        this.eventType = eventType;
        this.eventParams = eventParams;
        this.ts = System.currentTimeMillis();
    }

    public static <T> Event<T> from(Event<T> event) {
        if (event == null) {
            return null;
        }
        Event<T> e = new Event<>();
        e.setEventId(event.getEventId());
        e.setEventParams(event.getEventParams());
        e.setEventType(event.getEventType());
        e.setTs(event.getTs());
        return e;
    }
}

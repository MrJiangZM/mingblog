package com.ming.blog.event.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventTypeEnum {

    UNKNOWN(-1, "未知事件"),
    MOTION_DETECTION_TASK(2000, "测试任务队列"),
    ;

    private Integer eventType;
    private String desc;

}

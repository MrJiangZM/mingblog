package com.ming.blog.mq.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventTypeEnum {
    UNKNOWN(-1, "未知事件"),

    SERVICE_ALERT_PUBLISH(1000, "报警下发事件"),
    SERVICE_ALERT_PUBLISH_SHOP(1001, "报警下发-店铺地图用"),
    CHECK_ALERT(1002, "审核告警通知"),

    MOTION_DETECTION_TASK(2000, "移动侦测任务入库"),
    ;

    private int eventType;
    private String desc;
}

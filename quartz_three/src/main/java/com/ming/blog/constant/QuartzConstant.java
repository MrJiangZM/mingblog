package com.ming.blog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 3:07 下午
 */
@Getter
@AllArgsConstructor
public enum QuartzConstant {

    WAITING("WAITING", "默认状态"),
    ACQUIRED("ACQUIRED", "获得状态"),
    EXECUTING("EXECUTING", "运行中"),
    COMPLETE("COMPLETE", "完成"),
    BLOCKED("BLOCKED", "阻塞状态"),
    ERROR("ERROR", "错误"),
    PAUSED("PAUSED", "暂停"),
    PAUSED_BLOCKED("PAUSED_BLOCKED", "暂停阻塞状态"),
    DELETED("DELETED", "删除"),
    ;

//    WAITING	创建任务触发器默认状态
//    ACQUIRED	当到达触发时间时，获得状态
//    EXECUTING	运行中，firedTrigger表中
//    COMPLETE	完成状态，任务结束
//    BLOCKED	阻塞状态
//    ERROR	错误状态
//    PAUSED	暂停状态
//    PAUSED_BLOCKED	暂停阻塞状态，非并发下
//    DELETED	删除状态



    private String constant;
    private String desc;

}

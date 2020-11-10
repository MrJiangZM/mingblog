package com.ming.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 3:54 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzTrigger implements Serializable {

    private String schedName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String description;
    private Long nextFireTime;
    private Long priFireTime;
    private Integer priority;
    private String triggerState;
    private String triggerType;
    private Long startTime;
    private Long endTime;
    private String calendarName;
    private Integer misfireInstr;
    private String jobData;
    private Integer count;


    private String cron;
    private Long repeatInterval;
    private Long repeatCount;
}

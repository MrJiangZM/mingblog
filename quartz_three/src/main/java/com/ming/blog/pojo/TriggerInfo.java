package com.ming.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 2:40 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriggerInfo implements Serializable {

    //cron simple
    private String triggerType;
    //public
    private String triggerName;
    private String triggerGroup;
    private String description;
    private String jobClassName;    //执行业务类
    private String jobName;
    private String jobGroup;
    //cron trigger
    private String cronExpression;
    //simple trigger
    private Long startTime;
    private Long endTime;
    private Integer interval;
    private Integer repeatCount;


}

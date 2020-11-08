package com...pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobInfo {

    private String jobName;
    private String jobClassName;

    private String jobGroup;

    private String cronExpression;

    private String description;

    private String jobType;

    private Integer timeType;

    private List<TriggerInfo> triggerInfoList;

}

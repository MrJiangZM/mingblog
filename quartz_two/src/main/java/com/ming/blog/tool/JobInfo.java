package com.ming.blog.tool;

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

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String description;

    private String jobType;

    private Integer timeType;

    private List<TriggerInfo> triggerInfoList;

}

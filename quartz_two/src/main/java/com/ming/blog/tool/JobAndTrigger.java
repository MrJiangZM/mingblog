package com.ming.blog.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 *
 * @author jiangzaiming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAndTrigger {

    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String description;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cornExpression;
    private String timeZoneId;
    private String triggerState;
}

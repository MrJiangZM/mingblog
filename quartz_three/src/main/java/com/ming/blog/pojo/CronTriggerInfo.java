package com.ming.blog.pojo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 12:18 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CronTriggerInfo implements Serializable {

    private String triggerName;
    private String triggerGroup;
    private String cronExpression;
    private String timeZoneId;

}

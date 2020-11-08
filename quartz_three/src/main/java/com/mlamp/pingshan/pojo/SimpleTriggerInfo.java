package com...pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 12:26 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTriggerInfo implements Serializable {

    private String triggerName;
    private String triggerGroup;
    private Long repeatCount;
    private Long repeatInterval;
    private Long timesTriggered;

}

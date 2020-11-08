package com.ming.blog.tool;

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

    private String triggerName;
    private String triggerGroupName;
    private String description;
    private String cornDescription;
    private String jobName;
    private String jobGroupName;


}

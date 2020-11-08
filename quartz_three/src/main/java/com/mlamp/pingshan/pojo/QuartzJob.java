package com...pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 3:55 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzJob implements Serializable {

    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String description;

}

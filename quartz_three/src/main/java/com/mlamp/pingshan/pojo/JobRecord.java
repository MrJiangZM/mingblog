package com...pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 5:18 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_record")
public class JobRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String triggerName;
    private String triggerGroup;
    private String triggerDescription;
    private String jobName;
    private String jobDescription;
    private String jobGroup;
    private Long startTime;
    private String result;
    private String traceId;
    private Long endTime;

}

package com.ming.blog.pojo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 5:15 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "job_record")
public class JobRecord11 implements Serializable {

    private Integer id;
    private String triggerName;
    private String triggerGroup;
    private Date createTime;
    private Date updateTime;
    private String recordTotal;

}

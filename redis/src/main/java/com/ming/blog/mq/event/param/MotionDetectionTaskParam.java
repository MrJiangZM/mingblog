package com.ming.blog.mq.event.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotionDetectionTaskParam implements Serializable {
    //移动侦测任务唯一标识
    private Long taskId;
    //设备序列号
    private String sn;
    //通道号
    private Integer channelNo;
    //资源唯一标识
    private String url;
    //开始时间
    private Long startAt;
    //结束时间
    private Long endAt;
}

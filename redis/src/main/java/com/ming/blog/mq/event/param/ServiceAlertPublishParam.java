package com.ming.blog.mq.event.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jiangzaiming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAlertPublishParam implements Serializable {
    //报警唯一标识
    private Integer id;
    //报警类型
    private Integer alertType;
    //报警店铺
    private Integer shopId;
}

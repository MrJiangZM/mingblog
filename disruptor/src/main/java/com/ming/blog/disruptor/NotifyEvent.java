package com.ming.blog.disruptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:04 下午
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotifyEvent implements Serializable, Cloneable {

    private String message;

}

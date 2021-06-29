package com.ming.blog.disruptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:04 下午
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestEvent implements Serializable, Cloneable {

    private Integer id;
    private String name;

}

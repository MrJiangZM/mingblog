package com.ming.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/6/4 4:50 下午
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Test implements Serializable, Cloneable {

    private Integer id;
    private String name;

}

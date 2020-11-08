package com.blog.mq.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity implements Serializable {

    private String code;
    private Date time;
    private Integer id;
    private Long msg;

}

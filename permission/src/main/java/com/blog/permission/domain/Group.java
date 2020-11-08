package com.blog.permission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_group")
public class Group implements Serializable {

    private Integer id;
    private String code;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private Integer type;

}

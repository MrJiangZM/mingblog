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
@Table(name = "t_group_user")
public class GroupUser implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}

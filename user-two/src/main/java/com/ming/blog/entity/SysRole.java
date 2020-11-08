package com.ming.blog.entity;

/*
-- 角色
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
	`code` varchar(100) COMMENT '角色名称',
  `name` varchar(100) COMMENT '角色名称',
  `remark` varchar(100) COMMENT '备注',
  `dept_id` bigint(20) COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:22 上午
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String name;
    private String remark;
    private Long deptId;

}

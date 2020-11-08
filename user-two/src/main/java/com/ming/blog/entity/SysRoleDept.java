package com.ming.blog.entity;

import com.sun.tools.javah.Gen;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/*
-- 角色与部门对应关系
CREATE TABLE `sys_role_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `dept_id` bigint COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

 */
/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:35 上午
 */
@Data
@Entity
@Table(name = "sys_role_dept")
public class SysRoleDept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roleId;
    private Long deptId;

}

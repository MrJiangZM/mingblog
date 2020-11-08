package com.ming.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
/*
-- 部门
CREATE TABLE `sys_dept`  (
  `id` bigint(50) NOT NULL,
  `p_id` bigint(50) NULL DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `order_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  `dept_type_id` bigint(20) NULL DEFAULT NULL COMMENT '部门类别id',
  `level` int(10) NULL DEFAULT NULL COMMENT '部门级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

 */
/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:26 上午
 */
@Data
@Entity
@Table(name = "sys_dept")
public class SysDept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long pid;
    private String name;
    private String orderNum;
    private Integer status;
    private Long deptTypeId;
    private Integer level;

}

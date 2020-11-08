package com.ming.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/*

-- 用户与角色对应关系
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

 */
/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:24 上午
 */
@Data
@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long roleId;

}

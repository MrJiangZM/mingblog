package com.ming.blog.dao;

import com.ming.blog.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:39 上午
 */
@Repository
public interface RoleMenuDao extends JpaRepository<SysRoleMenu, Long> {
}

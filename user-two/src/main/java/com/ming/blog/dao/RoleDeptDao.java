package com.ming.blog.dao;

import com.ming.blog.entity.SysRoleDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:38 上午
 */
@Repository
public interface RoleDeptDao extends JpaRepository<SysRoleDept, Long> {
}

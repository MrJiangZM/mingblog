package com.ming.blog.dao;

import com.ming.blog.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 5:54 下午
 */
@Repository
public interface RoleDao extends JpaRepository<SysRole, Long> {
}

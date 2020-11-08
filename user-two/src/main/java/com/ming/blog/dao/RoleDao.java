package com.ming.blog.dao;

import com.ming.blog.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:38 上午
 */
@Repository
public interface RoleDao extends JpaRepository<SysRole, Long> {

    @Query(nativeQuery = true,
            value = "select * from sys_role r left join sys_user_role ur " +
                    "on r.id = ur.role_id where ur.user_id = ?1 ")
    List<SysRole> findRoleByUserId(Long userId);
}

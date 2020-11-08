package com.blog.permission.dao;

import com.blog.permission.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query(nativeQuery = true,
            value = "select r.* from t_role r left join t_user_role ur " +
                    "on r.id = ur.role_id where r.status =0 and ur.status = 0")
    List<Role> getRoleByUser(Integer userId);

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "update t_user_role ur set status = 2, update_time = :date where role_id = :roleId")
    void deleteRole(Integer roleId, Date date);
}

package com.blog.permission.dao;

import com.blog.permission.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "update t_user_role ur set status = 2, update_time = :date where user_id = :userId")
    void deleteUserRoleRelation(Integer userId, Date date);
}

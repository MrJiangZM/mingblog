package com.blog.permission.dao;

import com.blog.permission.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "update t_group g set status = 2, update_time = :date where id = :groupId")
    int deleteGroup(Integer groupId, Date date);

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "update t_group_user g set status = 2, update_time = :date where user_id = :userId")
    void deleteGroupUser(Integer userId, Date date);
}

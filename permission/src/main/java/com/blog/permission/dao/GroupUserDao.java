package com.blog.permission.dao;

import com.blog.permission.domain.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserDao extends JpaRepository<GroupUser,Integer> {
}

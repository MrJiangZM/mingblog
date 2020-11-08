package com.blog.permission.dao;

import com.blog.permission.domain.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRoleDao extends JpaRepository<GroupRole, Integer> {
}

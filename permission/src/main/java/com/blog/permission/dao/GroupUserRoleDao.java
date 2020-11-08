package com.blog.permission.dao;

import com.blog.permission.domain.GroupUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRoleDao extends JpaRepository<GroupUserRole, Integer> {
}

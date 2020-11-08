package com.ming.blog.order.dao.secondary;

import com.ming.blog.order.pojo.secondary.RoleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleOrderDao extends JpaRepository<RoleOrder, Integer> {
}

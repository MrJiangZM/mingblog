package com.ming.blog.money.dao.secondary;

import com.ming.blog.money.pojo.secondary.RoleMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMoneyDao extends JpaRepository<RoleMoney, Integer> {
}

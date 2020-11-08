package com.ming.blog.money.dao.secondary;

import com.ming.blog.money.pojo.secondary.UserMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoneyDao extends JpaRepository<UserMoney, Integer> {
}

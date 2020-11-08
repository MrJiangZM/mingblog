package com.ming.blog.money.dao.primary;

import com.ming.blog.money.pojo.primary.StudentMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMoneyDao extends JpaRepository<StudentMoney, Integer> {
}

package com.ming.blog.order.dao.primary;

import com.ming.blog.order.pojo.primary.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderDao extends JpaRepository<StudentOrder, Integer> {
}

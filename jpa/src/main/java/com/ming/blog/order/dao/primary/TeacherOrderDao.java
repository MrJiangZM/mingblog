package com.ming.blog.order.dao.primary;

import com.ming.blog.order.pojo.primary.TeacherOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherOrderDao extends JpaRepository<TeacherOrder, Integer> {
}

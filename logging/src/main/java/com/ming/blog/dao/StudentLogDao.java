package com.ming.blog.dao;

import com.ming.blog.domain.StudentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLogDao extends JpaRepository<StudentLog, Integer> {
}

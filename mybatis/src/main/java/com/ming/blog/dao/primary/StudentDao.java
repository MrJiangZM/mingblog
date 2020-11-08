package com.ming.blog.dao.primary;

import com.ming.blog.pojo.primary.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    List<Student> findAll();

    int insert(Student s);
}

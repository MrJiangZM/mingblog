package com.ming.blog.dao;

import com.ming.blog.anno.DataSourceType;
import com.ming.blog.pojo.primary.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    @DataSourceType("primaryDataSource")
    List<Student> findAll();

    @DataSourceType("primaryDataSource")
    int insert(Student s);
}

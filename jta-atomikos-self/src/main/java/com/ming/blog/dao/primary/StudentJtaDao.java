package com.ming.blog.dao.primary;

import com.ming.blog.pojo.primary.StudentJta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentJtaDao {

    List<StudentJta> findAll();

    int insert(StudentJta s);
}

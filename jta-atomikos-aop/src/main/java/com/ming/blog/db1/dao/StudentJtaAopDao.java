package com.ming.blog.db1.dao;

import com.ming.blog.anno.DataSourceTypeAnno;
import com.ming.blog.db1.domain.StudentJtaAop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentJtaAopDao {

    @DataSourceTypeAnno("primaryDataSource")
    List<StudentJtaAop> findAll();

    @DataSourceTypeAnno("primaryDataSource")
    int insert(StudentJtaAop studentJtaAop);
}

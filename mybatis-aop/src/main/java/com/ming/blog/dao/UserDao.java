package com.ming.blog.dao;

import com.ming.blog.anno.DataSourceType;
import com.ming.blog.pojo.secondary.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    @DataSourceType("secondaryDataSource")
    List<User> findAll();

    @DataSourceType("secondaryDataSource")
    int insert(User u);
}

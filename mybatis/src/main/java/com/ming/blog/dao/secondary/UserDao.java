package com.ming.blog.dao.secondary;

import com.ming.blog.pojo.secondary.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    List<User> findAll();

    int insert(User u);
}

package com.ming.blog.dao.secondary;

import com.ming.blog.pojo.secondary.UserJta;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserJtaDao {

    List<UserJta> findAll();

    int insert(UserJta u);
}

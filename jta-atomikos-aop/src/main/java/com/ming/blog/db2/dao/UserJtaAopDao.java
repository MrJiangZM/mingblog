package com.ming.blog.db2.dao;

import com.ming.blog.anno.DataSourceTypeAnno;
import com.ming.blog.db2.domain.UserJtaAop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserJtaAopDao {

    @DataSourceTypeAnno("secondaryDataSource")
    List<UserJtaAop> findAll();

    @DataSourceTypeAnno("secondaryDataSource")
    int insert(UserJtaAop userJtaAop);
}

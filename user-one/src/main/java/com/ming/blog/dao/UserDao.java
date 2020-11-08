package com.ming.blog.dao;

import com.ming.blog.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 5:52 下午
 */
@Repository
public interface UserDao extends JpaRepository<SysUser,Long> {
}

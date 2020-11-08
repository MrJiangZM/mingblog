package com.ming.blog.dao;

import com.ming.blog.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 5:53 下午
 */
@Repository
public interface MenuDao extends JpaRepository<SysMenu, Long> {
}

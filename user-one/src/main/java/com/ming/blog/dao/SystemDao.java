package com.ming.blog.dao;

import com.ming.blog.domain.SysSystem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 5:54 下午
 */
public interface SystemDao extends JpaRepository<SysSystem, Long> {
}

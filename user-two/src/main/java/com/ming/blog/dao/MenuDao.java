package com.ming.blog.dao;

import com.ming.blog.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:37 上午
 */
@Repository
public interface MenuDao extends JpaRepository<SysMenu, Long> {

    @Query(nativeQuery = true,
            value = "select * from sys_menu where  sys_user_role left join sys_role_menu on ")
    List<SysMenu> queryAllMenu(Long userId);

    @Query(nativeQuery = true,
            value = "select * from sys_menu m left join  sys_role_menu rm " +
                    "on m.id =  rm.menu_id where rm.role_id = ?1")
    List<SysMenu> queryByRoleId(Long roleId);
}

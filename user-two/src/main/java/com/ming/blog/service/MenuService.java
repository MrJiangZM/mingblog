package com.ming.blog.service;

import com.beust.jcommander.internal.Lists;
import com.ming.blog.dao.MenuDao;
import com.ming.blog.entity.SysMenu;
import com.ming.blog.entity.SysRole;
import com.ming.blog.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:41 上午
 */
@Service
public class MenuService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDao menuDao;


    public List<SysMenu> getUserMenuList(Long userId) {
        // 查询用户角色
        List<SysRole> roleList = roleService.findRoleByUserId(userId);
        List<SysMenu> allMenuList = Lists.newArrayList();
        for (SysRole role : roleList) {
            List<SysMenu> menus = queryAllMenuByRoleId(role.getId());
            allMenuList.addAll(menus);
        }
        //去重
        List<SysMenu> resultList = allMenuList.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(SysMenu :: getId))), ArrayList :: new));
        List<SysMenu> r = Lists.newArrayList();
        List<SysMenu> rootMenu = resultList.stream().filter(
                menu -> menu.getPid().equals(0)).collect(Collectors.toList());
        // 递归获取层级结构
        getMenuTree(rootMenu, resultList);
        return rootMenu;
    }

    /**
     * 递归找出目录的层级结构
     * @param rootMenu
     * @param allMenu
     * @return
     */
    private void getMenuTree(List<SysMenu> rootMenu, List<SysMenu> allMenu) {
        for (SysMenu menu : rootMenu) {
            List<SysMenu> lowerMenuList = getLowerMenu(menu.getId(), allMenu);
            if (CollectionUtils.isEmpty(lowerMenuList) && !menu.getPid().equals(0)) {
                return;
            }
            menu.setMenuList(lowerMenuList);
            getMenuTree(lowerMenuList, allMenu);
        }
    }

    private List<SysMenu> getLowerMenu(Long id, List<SysMenu> allMenu) {
        List<SysMenu> lowerMenuList = allMenu.stream().filter(
                menu -> menu.getPid().equals(id)).collect(Collectors.toList());
        return lowerMenuList;
    }

    private List<SysMenu> queryAllMenuByRoleId(Long roleId) {
        return menuDao.queryByRoleId(roleId);
    }

    private List<SysMenu> queryAllMenu(Long userId) {
        return menuDao.queryAllMenu(userId);
    }


}

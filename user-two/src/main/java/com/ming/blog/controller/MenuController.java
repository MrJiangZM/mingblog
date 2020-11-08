package com.ming.blog.controller;

import com.ming.blog.entity.SysMenu;
import com.ming.blog.service.MenuService;
import com.ming.blog.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 11:43 上午
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/nav")
    public Object nav(@RequestHeader("userId") Long userId){
        List<SysMenu> menuList = menuService.getUserMenuList(userId);
        return menuList;
    }

}

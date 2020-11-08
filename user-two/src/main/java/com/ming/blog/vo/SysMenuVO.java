package com.ming.blog.vo;

import com.ming.blog.entity.SysMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/7 1:52 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuVO extends SysMenu {

    private List<SysMenu> menuList;

}

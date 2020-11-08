package com.blog.permission.controller;

import com.blog.permission.domain.Role;
import com.blog.permission.povo.RolePO;
import com.blog.permission.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list/{userId}")
    public Object getRoleByUser(@PathVariable("userId") Integer userId) {
        List<Role> roleList = roleService.getRoleByUser(userId);
        return roleList;
    }

    @GetMapping("/all")
    public Object getAllRole() {
        List<Role> roleList =  roleService.getAllRole();
        return roleList;
    }

    @PostMapping("/add")
    public void add(@RequestBody RolePO rolePO) {
        roleService.add(rolePO);
    }

    @PostMapping("/del")
    public void del(@RequestBody Integer roleId) {
        roleService.del(roleId);
    }

}

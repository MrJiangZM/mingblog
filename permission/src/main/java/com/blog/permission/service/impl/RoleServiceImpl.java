package com.blog.permission.service.impl;

import com.blog.permission.config.ServerException;
import com.blog.permission.dao.GroupRoleDao;
import com.blog.permission.dao.RoleDao;
import com.blog.permission.domain.GroupRole;
import com.blog.permission.domain.GroupUser;
import com.blog.permission.domain.Role;
import com.blog.permission.povo.RolePO;
import com.blog.permission.service.RoleService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private GroupRoleDao groupRoleDao;

    @Override
    public List<Role> getRoleByUser(Integer userId) {
        return roleDao.getRoleByUser(userId);
    }

    @Override
    public List<Role> getAllRole() {

        return roleDao.findAll((root, query, cb) ->
                cb.and(new ArrayList<Predicate>() {{
                    add(cb.equal(root.get("status").as(Integer.class), 0));
                }}.toArray(new Predicate[0])));

        // Jpa多条件复杂查询的基本方式，可以简化为上面的方式，只是lamda表达式的简单实用
        // 最直接的方式是使用@query注解，然后使用原生的sql写，灵活且方便，因人而异
        /**
         Integer status = 0;
         Specification<Role> roleSpec = new Specification<Role>() {
         List<Predicate> predicateList =  Lists.newArrayList();
         @Override
         public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
         if (status != null) {
         predicateList.add(cb.equal(root.get("status").as(Integer.class), status));
         }
         Predicate[] predicateArr = new Predicate[predicateList.size()];
         return cb.and(predicateList.toArray(predicateArr));
         }
         };
         return roleDao.findAll(roleSpec);
         */


    }

    @Override
    public void add(RolePO rolePO) {
        Role role = Role.builder().name(rolePO.getName()).code(rolePO.getCode())
                .createTime(new Date()).updateTime(new Date()).build();
        Role save = roleDao.save(role);
        List<GroupRole> groupRoles = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(rolePO.getGroupIds())) {
            rolePO.getGroupIds().forEach(id -> {
                GroupRole groupRole = GroupRole.builder().roleId(save.getId()).groupId(id)
                        .createTime(new Date()).updateTime(new Date()).status(0).build();
                groupRoles.add(groupRole);
            });
        }
        groupRoleDao.saveAll(groupRoles);
    }

    @Override
    public void del(Integer roleId) {
//        Role role = roleDao.findById(roleId).orElseThrow(() -> new ServerException(40002, "找不到实体"));
        roleDao.deleteRole(roleId, new Date());

    }
}

package com.blog.permission.service.impl;

import com.blog.permission.config.ServerException;
import com.blog.permission.dao.*;
import com.blog.permission.domain.GroupUser;
import com.blog.permission.domain.GroupUserRole;
import com.blog.permission.domain.Role;
import com.blog.permission.domain.User;
import com.blog.permission.povo.UserPO;
import com.blog.permission.service.UserService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private GroupUserDao groupUserDao;
    @Autowired
    private GroupUserRoleDao groupUserRoleDao;

    @Override
    public void addUser(UserPO userPO) {
        User user = User.builder().name(userPO.getName()).age(10).password(userPO.getPassword()).build();
        User save = userDao.save(user);
        // 添加用户组关联数据
        List<GroupUserRole> groupUserRoleList = Lists.newArrayList();
//        if (!CollectionUtils.isEmpty(userPO.getGroupIds())) {
//            userPO.getGroupIds().forEach(id -> {
//                GroupUser groupUser = GroupUser.builder().userId(save.getId()).groupId(id)
//                        .createTime(new Date()).updateTime(new Date()).status(0).build();
//                groupUsers.add(groupUser);
//            });
//        }
//        for (userPO.) {
//
//        }
//        groupUserDao.saveAll(groupUsers);
        log.info("{}", save);
    }

    @Override
    public List<Role> getRoleByUser(Integer userId) {
        return roleDao.getRoleByUser(userId);
    }

    @Override
    @Transactional
    public void delUser(Integer userId) throws ServerException {
        Optional<User> userOptional = userDao.findById(userId);
        userOptional.orElseThrow(() -> new ServerException(40001, "找不到实体"));
        userOptional.ifPresent(user -> {
            user.setUpdateTime(new Date());
            user.setStatus(2);
            userDao.save(user);
        });
//        userDao.deleteUserRoleRelation(userId, new Date());

    }
}

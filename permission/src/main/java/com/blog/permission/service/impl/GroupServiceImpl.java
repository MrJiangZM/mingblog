package com.blog.permission.service.impl;

import com.blog.permission.dao.GroupDao;
import com.blog.permission.dao.GroupUserDao;
import com.blog.permission.domain.Group;
import com.blog.permission.domain.GroupUser;
import com.blog.permission.povo.GroupPO;
import com.blog.permission.service.GroupService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private GroupUserDao groupUserDao;

    @Override
    public void add(GroupPO groupPO) {
        Group group = Group.builder().code(groupPO.getCode())
                .name(groupPO.getName()).type(groupPO.getType()).build();
        groupDao.save(group);
    }

    @Override
    public void del(Integer groupId) {
        groupDao.deleteGroup(groupId, new Date());
        // 删除一些组的关联关系  和用户的关联   和角色的关联
//        groupDao
    }

    @Override
    public void assign(GroupPO groupPO) {
        groupDao.deleteGroupUser(groupPO.getUserId(), new Date());
        if (!CollectionUtils.isEmpty(groupPO.getGroups())) {
            List<GroupUser> groupUsers = Lists.newArrayList();
            groupPO.getGroups().forEach(id -> {
                GroupUser groupUser = GroupUser.builder().userId(groupPO.getUserId())
                        .groupId(id).status(0).createTime(new Date())
                        .updateTime(new Date()).build();
                groupUsers.add(groupUser);
            });
            groupUserDao.saveAll(groupUsers);
        }
    }
}

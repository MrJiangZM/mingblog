package com.blog.permission.service;

import com.blog.permission.povo.GroupPO;

public interface GroupService {

    void add(GroupPO groupPO);

    void del(Integer groupId);

    void assign(GroupPO groupPO);

}

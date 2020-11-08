package com.ming.blog.service;

import com.ming.blog.dao.UserDao;
import com.ming.blog.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 11:44 上午
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void add(SysUser user) {
        userDao.save(user);
    }

//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private SystemService systemService;
//    @Autowired
//    private PermissionService permissionService;
//
//    public Object getUserInfo(Integer userId) {
//        User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException());
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("user", user);
//        List<Role> roles = roleService.getRolesByUserId(userId);
//        List<RoleInfo> roleInfos = Lists.newArrayList();
//        for (Role role : roles) {
//            List<Permission> permissions = permissionService.getByRole(role);
//            RoleInfo roleInfo = new RoleInfo(role);
//            roleInfo.setPermissions(permissions);
//            roleInfos.add(roleInfo);
//        }
//        Map<Integer, List<RoleInfo>> sysRolesMap = roleInfos.stream().collect(Collectors.groupingBy(RoleInfo :: getSystemId));
//        Set<Integer> integers = sysRolesMap.keySet();
//        List<SysSystem> systems = systemService.findSystemList(integers);
//        List<SystemInfo> systemInfoList = systems.stream().map(system -> {
//            SystemInfo systemInfo = new SystemInfo(system);
//            systemInfo.setSysRoles(sysRolesMap.get(system.getId()));
//            return systemInfo;
//        }).collect(Collectors.toList());
//        map.put("systems", systemInfoList);
//        return map;
//    }

}

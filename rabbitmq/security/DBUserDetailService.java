package com.ming.blog.config.security;

import com.google.common.collect.Lists;
import com.ming.blog.domain.Role;
import com.ming.blog.domain.UserDB;
import com.ming.blog.service.RoleService;
import com.ming.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 2:15 下午
 */
@Slf4j
@Component
public class DBUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDB userDB =  userService.findByUsername(username);
        if (userDB == null) {
            log.error("cannot find user by username {}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Role> roles = roleService.findRoles(userDB.getId());
        Collection<GrantedAuthority> authorities = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(roles)) {
            authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getCode()))
                    .collect(Collectors.toList());
        }
        SecurityUser securityUser = new SecurityUser(username,
                userDB.getPassword(),
                authorities);
        return securityUser;
    }
}

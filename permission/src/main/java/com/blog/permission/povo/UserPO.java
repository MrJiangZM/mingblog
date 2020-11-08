package com.blog.permission.povo;

import com.blog.permission.domain.GroupRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPO implements Serializable {

    private String name;
    private String password;
    private Integer age;
    private Integer gender;

    private List<GroupRolePO> groups;

}

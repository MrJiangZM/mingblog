package com.blog.permission.povo;

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
public class GroupPO implements Serializable {

    private String code;
    private String name;
    private Integer type;

    private Integer groupId;
    private List<Integer> groupIds;

    private Integer userId;
    private List<Integer> userIds;

    private Integer roleId;
    private List<Integer> roleIds;

}

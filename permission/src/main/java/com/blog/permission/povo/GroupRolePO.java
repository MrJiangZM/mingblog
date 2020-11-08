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
public class GroupRolePO implements Serializable {

    private Integer groupId;
    private List<Integer> roleIds;

}

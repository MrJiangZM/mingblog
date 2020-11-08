package com.blog.permission.povo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePO implements Serializable {

    private String name;
    private String code;
    private Integer type;

    private List<Integer> groupIds;

}

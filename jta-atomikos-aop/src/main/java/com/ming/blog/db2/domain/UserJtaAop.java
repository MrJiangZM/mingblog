package com.ming.blog.db2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJtaAop implements Serializable {

    private Integer id;
    private String name;
    private String password;

}

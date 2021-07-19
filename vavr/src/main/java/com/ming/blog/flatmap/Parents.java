package com.ming.blog.flatmap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parents {

    private String id;
    private String name;
    private String chirldId;
    private String email;

}
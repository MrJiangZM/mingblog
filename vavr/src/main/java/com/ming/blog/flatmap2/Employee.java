package com.ming.blog.flatmap2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MrJiangZM
 * @since <pre>2021/7/19</pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private Integer salary;

}

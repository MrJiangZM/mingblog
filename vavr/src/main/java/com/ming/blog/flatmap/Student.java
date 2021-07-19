package com.ming.blog.flatmap;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode // (callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    //    @EqualsAndHashCode.Include
    private String id;
    //    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Exclude
    private String techId;

    //重写hashCode及equals方法(id及name相同就为同一个学生)
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return id.equals(student.id) &&
//                name.equals(student.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }


}
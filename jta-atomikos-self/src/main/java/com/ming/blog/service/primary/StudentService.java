package com.ming.blog.service.primary;

import com.ming.blog.pojo.primary.StudentJta;

import java.util.HashMap;
import java.util.List;

public interface StudentService {

    HashMap<String, Object> findAll();

    void insert();

    List<StudentJta> findAllS();
}

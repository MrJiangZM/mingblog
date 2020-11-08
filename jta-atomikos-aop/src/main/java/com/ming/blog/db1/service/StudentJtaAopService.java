package com.ming.blog.db1.service;

import com.ming.blog.db1.domain.StudentJtaAop;

import java.util.List;

public interface StudentJtaAopService {
    List<StudentJtaAop> findAll();
    int insert(StudentJtaAop studentJtaAop);
}

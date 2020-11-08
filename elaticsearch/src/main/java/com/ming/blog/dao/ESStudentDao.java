package com.ming.blog.dao;

import com.ming.blog.domain.es.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESStudentDao extends ElasticsearchRepository<Student, Long> {

    Iterable<Student> findByName(String str);

    Iterable<Student> findByNameContains(String str);

}

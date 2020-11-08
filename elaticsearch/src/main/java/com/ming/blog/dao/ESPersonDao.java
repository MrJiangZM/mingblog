package com.ming.blog.dao;

import com.ming.blog.domain.es.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESPersonDao extends ElasticsearchRepository<Person, Integer> {
}

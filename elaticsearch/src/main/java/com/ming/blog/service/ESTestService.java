package com.ming.blog.service;

import com.ming.blog.domain.es.Person;
import com.ming.blog.domain.es.Student;

import java.util.List;

public interface ESTestService  <T> {

    List<Person> find();

    void createIndex(Class<T> clazz);

    List<Person> findPerson();

    List<Student> findStudent();

    void add();

    void update();

    void delete();

    List<Student> like(String str);

    List contain(String str);

    List search(String str);

    List searchKey(String str, Integer pageNum, Integer pageSize);

    /**
     * 全文搜索
     * @param a
     * @param page
     * @param size
     * @return
     */
    List<Student> searchAll(String a, Integer page, Integer size);

    /**
     * 模糊查询
     * @param str
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Student> likeKey(String str, Integer pageNum, Integer pageSize);

    /**
     * 多条件组合的查询
     * @param str
     * @return
     */
    List<Student> query(String str);

    /**
     * 包裹查询，高于设定的分数
     * @param str
     * @return
     */
    List<Student> containQuery(String str);

    /**
     * disMax查询
     * 对子查询的结果做union, score沿用子查询score的最大值,
     * 广泛用于muti-field查询
     */
    List<Student> disMaxQuery();

    /**
     * 父子文档查询
     *
     * 需要构造配置父子文档
     *
     * @return
     */
    List<Student> hasChildQuery();

}

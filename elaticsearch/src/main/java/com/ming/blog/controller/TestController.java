package com.ming.blog.controller;

import com.ming.blog.domain.es.Person;
import com.ming.blog.domain.es.Student;
import com.ming.blog.service.ESTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private ESTestService esTestService;

    @RequestMapping("/find/person")
    public List<Person> test1() {
        return esTestService.findPerson();
    }


    @RequestMapping("/find/student")
    public List<Student> test2() {
        return esTestService.findStudent();
    }

    @GetMapping("/index/create")
    public void createIndex() {
        System.out.println("=====================");
        esTestService.createIndex(Person.class);
    }

    @GetMapping("/index/delete")
    public void deleteIndex() {
        System.out.println("=====================");
        esTestService.createIndex(Person.class);
    }


    @GetMapping("/add")
    public void add() {
        System.out.println("=====================");
        esTestService.add();
    }

    @GetMapping("/update")
    public void update() {
        System.out.println("=====================");
        esTestService.update();
    }


    @GetMapping("/delete")
    public void delete() {
        System.out.println("=====================");
        esTestService.delete();
    }

    @GetMapping("/find/like")
    public List like(@RequestParam("str") String str) {
        System.out.println("=====================");
        return esTestService.like(str);
    }

    @GetMapping("/find/contain")
    public List contain(@RequestParam("str") String str) {
        System.out.println("=====================");
        return esTestService.contain(str);
    }

    @GetMapping("/find/search")
    public List search(@RequestParam("str") String str) {
        System.out.println("=====================");
        return esTestService.search(str);
    }

    @GetMapping("/find/searchKey")
    public List searchkey(@RequestParam("str") String str,
                          @RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize) {
        System.out.println("=====================");
        return esTestService.searchKey(str, pageNum, pageSize);
    }

    @GetMapping("/find/likeKey")
    public List likeKey(@RequestParam("str") String str,
                          @RequestParam("pageNum") Integer pageNum,
                          @RequestParam("pageSize") Integer pageSize) {
        System.out.println("=====================");
        return esTestService.likeKey(str, pageNum, pageSize);
    }


}

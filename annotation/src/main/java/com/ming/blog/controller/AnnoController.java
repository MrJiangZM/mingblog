package com.ming.blog.controller;

import com.ming.blog.anno.JustTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/anno")
@RestController
public class AnnoController {

    @JustTest("ok")
    @GetMapping("/test/test1")
    public String test1() {
        System.out.println("test1 执行了");
        return "success";
    }

    @JustTest("no")
    @GetMapping("/test/test2")
    public String test2() {
        System.out.println("test2 执行了");
        return "success";
    }

    @GetMapping("/test3")
    public String test3() {
        System.out.println("test3 执行了");
        return "success";
    }

    @JustTest
    @GetMapping("/test4")
    public String test4() {
        System.out.println("test4 执行了");
        return "success";
    }

}

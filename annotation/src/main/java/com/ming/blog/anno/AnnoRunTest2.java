package com.ming.blog.anno;

import lombok.Data;

@Data
public class AnnoRunTest2 {

    @JustTest(description = "名字啊")
    private String name;
    private String pass;

    @JustTest(value = "自己写的")
    public AnnoRunTest2(){
        System.out.println("空参构造执行了");
    }
    public AnnoRunTest2(String name, String pass){
        System.out.println("全参构造执行了");
        this.name = name;
        this.pass = pass;
    }

    @JustTest
    public void test1(String a, String b) {
        System.out.println("有注解的测试" + a + b);
    }

    public void test2() {
        System.out.println("没有注解的测试");
    }

}

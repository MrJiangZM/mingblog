package com.ming.blog.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class AnnoTest {

    public static void main(String[] args) throws Exception {
        testAnno(AnnoRunTest2.class);
    }

    private static void testAnno(Class<AnnoRunTest2> annoRunTest2Class) throws Exception {
        // 下面是method注解的测试
//        for (Method method : annoRunTest2Class.getMethods()) {
//            JustTest justTest = method.getAnnotation(JustTest.class);
//            if (justTest != null) {
//                System.out.println(method.getName());
//                System.out.println(justTest.description()+"===="+justTest.value());
//                AnnoRunTest2 runTest2 = annoRunTest2Class.newInstance();
////                method.invoke(runTest2, "1");
//                method.invoke(runTest2, "1", "2");
////                method.invoke(runTest2, "1", "2", "3");
//            } else {
//                System.out.println(method.getName());
//            }
//        }
        // +++++++++++++++++++++++++++++++++++++++++++++
        // 下面是field注解的测试
//        Field[] fields = annoRunTest2Class.getFields();
//        fields = annoRunTest2Class.getDeclaredFields();
//        for (Field field : fields) {
//            JustTest annotation = field.getAnnotation(JustTest.class);
//            if (annotation != null) {
//                System.out.println(field.getName());
//                System.out.println(annotation.description() + "-------" + annotation.value());
//            } else {
//                System.out.println(field.getName());
//                System.out.println("这个没有anno");
//            }
//        }
        // ========================================
        // Constructor注解的测试
        Constructor<?>[] constructors = annoRunTest2Class.getConstructors();
        for (Constructor con : constructors) {
            Annotation annotation = con.getAnnotation(JustTest.class);
            if (annotation != null) {
                System.out.println("这个有注解");
                AnnoRunTest2 o = (AnnoRunTest2) con.newInstance();
                System.out.println(o.getName());
            } else {
                System.out.println("这没有注解");
                AnnoRunTest2 runTest2 = (AnnoRunTest2) con.newInstance("a", "b");
                System.out.println(runTest2.getName());
            }
        }
    }

}

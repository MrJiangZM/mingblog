package com.ming.blog.flatmap2;

import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
public class EmployeeTestCase extends BaseTest {

    /*
        坑： IntStream 和 Stream<Integer> 的基本区别，尽量保证
     */
    public static void main(String[] args) {
//        List<Employee> employeesAll = listFlat.stream().flatMap(Collection :: stream).collect(Collectors.toList());
//        System.out.println(employeesAll.size() == 5);
//
//        List<Employee> employeesAll2 = listFlat.stream().flatMap(employees -> {
//            Stream<Employee> stream = employees.stream();
//            return stream;
//        }).collect(Collectors.toList());
//        System.out.println(employeesAll2.size() == 5);

        List<Long> listFlatLong = listFlat.stream()
                .flatMap(employees -> employees.stream())
                .flatMapToLong(employee -> LongStream.of(employee.getId()))
                .boxed()
                .collect(Collectors.toList());
        log.info("listFlatLong:{}", listFlatLong.toString());

        List<Integer> listFlatLong2 = listFlat.stream()
                .flatMap(employees -> employees.stream())
                .mapToInt(Employee :: getId)
                .boxed()

//                .map(Employee :: getId)

                .collect(Collectors.toList());
        log.info("listFlatLong2:{}", listFlatLong2.toString());

//
//        List<Integer> listFlatName = listFlat.stream()
//                .flatMap(employees -> employees.stream())
//                .peek(System.out :: println)
//                .map(employee -> employee.getSalary())
//                .peek(System.out :: println)
//                .collect(Collectors.toList());
    }


}
package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.ming.blog")
public class ElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
        log.info("app started");
        // 这就是这么回事
//        sort();
    }


//    public static void sort() {
//
//        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o1.getValue() - o2.getValue();
//            }
//        };
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 4);
//        map.put("c", 2);
//        map.put("e", 3);
//
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        List<Map.Entry<String, Integer>> collect = entries.stream().collect(Collectors.toList());
//        System.out.println(collect);
//        Collections.sort(collect, valueComparator);
//        System.out.println(collect);
//    }


}

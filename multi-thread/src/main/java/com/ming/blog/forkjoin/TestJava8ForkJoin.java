package com.ming.blog.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author MrJiangZM
 * @since <pre>2022/1/12</pre>
 */
public class TestJava8ForkJoin {

//    @Test
    public void test03() {

        Instant start = Instant.now();
//        LongStream.rangeClosed(0, 110)
//                //并行流
//                .parallel()
//                .reduce(0, Long :: sum);

        LongStream.rangeClosed(0, 110)
                //顺序流
                .sequential()
                .reduce(0, Long :: sum);

        Instant end = Instant.now();
        System.out.println("耗费时间" + Duration.between(start, end).toMillis());
    }

}

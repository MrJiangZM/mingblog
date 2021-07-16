package com.ming.blog;

import com.alibaba.fastjson.JSONObject;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.Tuple5;
import java.math.BigDecimal;

/**
 * @author MrJiangZM
 * @since <pre>2021/7/6</pre>
 */
public class VavrTest {

    public static void main(String[] args) {
        Tuple5<String, Integer, Double, Long, BigDecimal> test = Tuple.of("test", 1, 1.2, 1L, BigDecimal.valueOf(223));
        System.out.println(test);
        System.out.println(JSONObject.toJSONString(test));
//        Try.of(VavrTest :: test1)
//                .onSuccess(a -> {System.out.println("有异常啦");} )
//                .onFailure(System.out.println("有异常啦"));
//        Either
//        Function1
    }


    public static Integer test1() {
        Tuple3<Integer, Long, String> test = Tuple.of(1, 123L, "test");
        System.out.println(test);
        return 1;
    }

}

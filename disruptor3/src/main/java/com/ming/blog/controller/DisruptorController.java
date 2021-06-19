package com.ming.blog.controller;

import com.ming.blog.email.EmailData;
import com.ming.blog.email.EmailDataEventQueueHelper;
import com.ming.blog.order.OrderData;
import com.ming.blog.order.OrderDataEventQueueHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class DisruptorController {

    @Autowired
    private OrderDataEventQueueHelper orderDataEventQueueHelper;

    @Autowired
    private EmailDataEventQueueHelper emailDataEventQueueHelper;

    @GetMapping("/api/test1")
    public void demo() {
        System.out.println("1111111111111111!");
        orderDataEventQueueHelper.publishEvent(new OrderData(1L, "小明的", BigDecimal.ONE));
        orderDataEventQueueHelper.publishEvent(new OrderData(2L, "小刚的", BigDecimal.TEN));
        orderDataEventQueueHelper.publishEvent(new OrderData(3L, "小红的", BigDecimal.ZERO));
        orderDataEventQueueHelper.publishEvent(new OrderData(null, "小李的", BigDecimal.valueOf(123.12)));

    }

    @GetMapping("/api/test2")
    public void demo2() {
        System.out.println("22222222222222222!");
        emailDataEventQueueHelper.publishEvent(new EmailData(5L, "小明的", BigDecimal.ONE));
        emailDataEventQueueHelper.publishEvent(new EmailData(null, "小刚的", BigDecimal.TEN));
        emailDataEventQueueHelper.publishEvent(new EmailData(7L, "小红的", BigDecimal.ZERO));
        emailDataEventQueueHelper.publishEvent(new EmailData(7L, "小李的", BigDecimal.valueOf(123.12)));

    }

}
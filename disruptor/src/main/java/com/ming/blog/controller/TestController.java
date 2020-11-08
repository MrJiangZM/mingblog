package com.ming.blog.controller;

import com.ming.blog.disruptor.NotifyDisruptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:22 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private NotifyDisruptorService notifyDisruptorService;

    @GetMapping("/test1")
    public Object test1(String test) {
        notifyDisruptorService.sendNotify(test + "haafdsa");
        return "成功了啊";
    }

}

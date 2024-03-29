package com.ming.blog.controller;

import com.ming.blog.event.channel.RedisEventChannel;
import com.ming.blog.event.entity.EventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jiang Zaiming
 * @date 2020/6/4 4:27 下午
 */
@Slf4j
@RestController
//@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisEventChannel redisEventChannel;

    @GetMapping("/test1")
    public String test1() {
        redisTemplate.opsForValue().set("name", "111111111");
        return "test";
    }

    @GetMapping("/test2")
    public Object test2() {
        Object name = redisTemplate.opsForValue().get("name");
        return name;
    }

    @GetMapping("/test3")
    public Object test3() {
        redisEventChannel.publish(EventTypeEnum.MOTION_DETECTION_TASK, new Test(1, "小明"));
        log.error("发送成功");
        Object name = redisTemplate.opsForValue().get("name");
        return name;
    }

}

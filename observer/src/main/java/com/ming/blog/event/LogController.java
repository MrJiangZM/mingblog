package com.ming.blog.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@RestController
@RequestMapping("/test")
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping
    public Object test(String name, Integer age) {
        logService.doSomething(name, age);
        return "成功了啊";
    }

}

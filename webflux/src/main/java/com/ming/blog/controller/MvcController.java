package com.ming.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

/**
 * @author MrJiangZM
 * @since <pre>2021/7/15</pre>
 */
@RestController
@RequestMapping("/mvc")
public class MvcController {

    @GetMapping("/test1")
    public Object testMvc(String test) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", test);
        jsonObject.put("date", new Date());
        return jsonObject;
    }

}

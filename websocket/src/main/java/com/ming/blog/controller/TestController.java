package com.ming.blog.controller;

import com.ming.blog.config.WebsocketServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.net.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2019/11/26 2:36 下午
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        return "测试接口";
    }

    @Autowired
    private WebsocketServer websocketServer;

    /**
     * 客户端页面
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String idnex() {

        return "index";
    }

    /**
     * 服务端页面
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        int num = WebsocketServer.getOnlineNum();
        List<String> list = WebsocketServer.getOnlineUsers();

        model.addAttribute("num", num);
        model.addAttribute("users", list);
        return "admin";
    }

    /**
     * 个人信息推送
     *
     * @return
     */
    @RequestMapping("sendmsg")
    @ResponseBody
    public String sendmsg(String msg, String username) {
        //第一个参数 :msg 发送的信息内容
        //第二个参数为用户长连接传的用户人数
        String[] persons = username.split(",");
        WebsocketServer.SendMany(msg, persons);
        return "success";
    }

    /**
     * 推送给所有在线用户
     *
     * @return
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {
        WebsocketServer.sendAll(msg);
        return "success";
    }

}

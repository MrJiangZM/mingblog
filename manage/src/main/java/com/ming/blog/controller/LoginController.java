package com.ming.blog.controller;

import com...pojo.User;
import com...service.UserService;
import com...util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/auth")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    public ModelAndView login(@RequestParam("user_name") String userName, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        ModelAndView mv = new ModelAndView();
        try {
            subject.login(new UsernamePasswordToken(userName, password));
            User user = userService.findOneByTelephone(userName);
            user.setAccessToken(subject.getSession().getId().toString());
            subject.getSession().setTimeout(30 * 24 * 60 * 60 * 1000L);
            SessionUtil.setSession("user", user);
            mv.setViewName("redirect:/mzmanage/index");
        } catch (Exception e) {
            log.error(String.format("用户%s登陆失败", userName));
            mv.setViewName("login");
            mv.addObject("error", "用户名密码错误");
        }
        return mv;
    }

    @GetMapping("logout")
    @RequiresAuthentication
    public ModelAndView logout() {
        SecurityUtils.getSubject().logout();
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView toLoginPage() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
}

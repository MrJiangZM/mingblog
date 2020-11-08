package com.ming.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class IndexController {

    @RequestMapping(value = {"mzmanagerConst.COMMA"}, method = RequestMethod.GET)
    public ModelAndView toLogin() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = {"indexConst.COMMAmzmanager/index"}, method = RequestMethod.GET)
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = {"errorConst.COMMAmzmanager/error"}, method = RequestMethod.GET)
    public ModelAndView toError() {
        ModelAndView mv = new ModelAndView("error");
        return mv;
    }
}

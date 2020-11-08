package com.ming.blog.controller;

import com.ming.blog.domain.SysSystem;
import com.ming.blog.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 5:56 下午
 */
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @PostMapping("/add")
    public void add(@RequestBody SysSystem system) {
        systemService.add(system);
    }

    @PostMapping("/udpate")
    public void udpate(@RequestBody SysSystem system) {
        systemService.add(system);
    }

    @GetMapping("/find")
    public void find(@RequestParam Long id) {
        SysSystem system = systemService.find(id);
    }

    @PostMapping("/delete")
    public void delete(@RequestParam Long id) {
        systemService.delete(id);
    }


    @PostMapping("/list")
    public List<SysSystem> list() {
        return systemService.list();
    }

}

package com.ming.blog.controller;

import com.ming.blog.domain.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Zaiming
 * @date 2019/11/25 5:33 下午
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api("order相关的controller接口")
public class OrderController {

    @GetMapping("/test1")
    @ApiOperation(value = "根据id查询用户", notes = "web后台根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "int", value = "用户Id", required = true),
            @ApiImplicitParam(name = "name", paramType = "string", value = "用户名称", required = true),
            @ApiImplicitParam(name = "token", paramType = "header", value = "token", required = true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public User test1(Integer userId, String name) {
        User build = User.builder().userId(userId).name(name).build();
        return build;
    }

    @GetMapping("/test2")
    @ApiOperation(value = "根据id查询用户", notes = "web后台根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "int", value = "用户Id", required = true),
            @ApiImplicitParam(name = "name", paramType = "string", value = "用户名称", required = true)
    })
    public User test2(Integer userId, String name) {
        User build = User.builder().userId(userId).name(name).build();
        return build;
    }

}

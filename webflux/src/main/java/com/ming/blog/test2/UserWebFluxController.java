package com.ming.blog.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/user")
public class UserWebFluxController {
 
    @Autowired
    private UserHandler userHandler;
 
    /**
     * 根据ID查询某个User
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Mono<User> findUserById(@PathVariable("id") Long id) {
        return userHandler.findUserById(id);
    }
 
    /**
     * 查找所有User
     * @return
     */
    @GetMapping()
    public Flux<User> findAllUser() {
        return userHandler.findAllUser();
    }
 
    /**
     * 插入User
     * @param user
     * @return
     */
    @PostMapping()
    public Mono<User> saveUser(@RequestBody User user) {
        return userHandler.save(user);
    }
 
    /**
     * 修改User
     * @param user
     * @return
     */
    @PutMapping()
    public Mono<User> modifyUser(@RequestBody User user) {
        return userHandler.modifyUser(user);
    }
 
    /**
     * 根据ID删除User
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Mono<Boolean> deleteUser(@PathVariable("id") Long id) {
        userHandler.deleteUser(id);
        return Mono.create(test -> test.success(Boolean.TRUE));
    }
}

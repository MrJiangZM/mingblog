package com.ming.blog.test2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author：JCccc
 * @Description：
 * @Date： created in 16:38 2019/5/30
 */
@Component
public class UserHandler {
    private final UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Mono<User> save(User user) {
        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.save(user)));
    }

    public Mono<User> findUserById(Long id) {
        return Mono.justOrEmpty(userRepository.findById(id));
    }

    public Flux<User> findAllUser() {
        return Flux.fromIterable(userRepository.findAll());
    }

    public Mono<User> modifyUser(User user) {
        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.save(user)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
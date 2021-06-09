package com.ming.blog.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@Service
public class LogService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     *  注册一个event事件
     * @param name
     */
    public void doSomething(String name, Integer age) {
        System.out.println("user：" + name + " register!!!!!!!!" + age);
        applicationEventPublisher.publishEvent(new MtLogEvent(this, name, age));
        applicationEventPublisher.publishEvent(new TestEvent(this, name, age.toString()));
    }


}

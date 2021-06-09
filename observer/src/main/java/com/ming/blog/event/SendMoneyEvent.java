package com.ming.blog.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@Service
public class SendMoneyEvent implements ApplicationListener<MtLogEvent> {

    @Override
    @Async
    public void onApplicationEvent(MtLogEvent event) {
        System.out.println("sendMoney start " + event.getName() + ", age " + event.getAge());
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {

        }
        System.out.println("sendMoney finish " + event.getName() + ", age " + event.getAge());
    }

}

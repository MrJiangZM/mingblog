package com.ming.blog.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@Service
public class EmailService {

    @EventListener
    @Async
    public void sendEmail(MtLogEvent event) {
        System.out.println("sendEmail start " + event.getName() + ", age " + event.getAge());
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {

        }
        System.out.println("sendEmail finish " + event.getName() + ", age " + event.getAge());

    }

    @EventListener
    @Async
    public void saveDb(MtLogEvent event) {
        System.out.println("saveDb start " + event.getName() + ", age " + event.getAge());
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {

        }
        System.out.println("saveDb finish " + event.getName() + ", age " + event.getAge());

    }


    @EventListener
    @Async
    public void sendEmail(TestEvent event) {
        System.out.println("start title " + event.getTitle() + ", text " + event.getText());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {

        }
        System.out.println("finish title " + event.getTitle() + ", text " + event.getText());

    }


}

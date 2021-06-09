package com.ming.blog.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@Getter
public class TestEvent extends ApplicationEvent {

    private String title;
    private String text;

    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String title, String text) {
        super(source);
        this.text = text;
        this.title = title;
    }

}

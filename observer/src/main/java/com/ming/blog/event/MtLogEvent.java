package com.ming.blog.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/9</pre>
 */
@Getter
public class MtLogEvent extends ApplicationEvent {

    private String name;
    private Integer age;

    public MtLogEvent(String source) {
        super(source);
    }

    public MtLogEvent(Object source, String name, Integer age) {
        super(source);
        this.age = age;
        this.name = name;
    }

}

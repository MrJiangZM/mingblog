package com.ming.blog.email;

import com.ming.blog.order.OrderDataEvent;

public class EmailEventFactory implements com.lmax.disruptor.EventFactory<EmailDataEvent> {
	 
    public EmailDataEvent newInstance() {
        return new EmailDataEvent();
    }

}
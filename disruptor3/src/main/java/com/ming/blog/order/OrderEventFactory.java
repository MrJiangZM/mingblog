package com.ming.blog.order;

public class OrderEventFactory implements com.lmax.disruptor.EventFactory<OrderDataEvent> {
	 
    public OrderDataEvent newInstance() {
        return new OrderDataEvent();
    }

}
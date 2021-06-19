package com.ming.blog.order;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;

@Slf4j
public class OrderDataEventHandler implements WorkHandler<OrderDataEvent>, EventHandler<OrderDataEvent> {

    @Override
    public void onEvent(OrderDataEvent event) {
        if (event.getValue() == null || event.getValue().getId() == null) {
            log.warn("WorkHandler =======  receiver series data is empty!");
        } else if (BigDecimal.ZERO.equals(event.getValue().getPrice())) {
            log.info("WorkHandler =======  {}", event.getValue());
        } else {
            log.error("WorkHandler =======  Hey baby, hello world!");
        }

    }

    @Override
    public void onEvent(OrderDataEvent event, long l, boolean b) {
        if (event.getValue() == null || event.getValue().getId() == null) {
            log.warn("EventHandler ======= sequence=={}, boolean:{}  series data is empty!", l, b);
        } else if (BigDecimal.ZERO.equals(event.getValue().getPrice())) {
            log.info("EventHandler ======={}, sequence=={}, boolean:{}", event.getValue(), l, b);
        } else {
            log.error("EventHandler ======={} sequence=={}, boolean:{} Hey baby, hello world!", event.getValue(), l, b);
        }
    }
}
package com.ming.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * 暂时不起作用
 *
 * @author Jiang Zaiming
 * @date 2020/3/25 12:57 下午
 */
@Slf4j
//@ConditionalOnProperty(havingValue = "true")
//@Component
public class ApplicationCloseConfig
        implements ApplicationListener<ContextClosedEvent>,
        ApplicationContextAware, BeanPostProcessor {

    @Autowired
    private ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (contextClosedEvent instanceof ContextClosedEvent) {
//            log.info("event ContextClosedEvent");
//            SchedulerConfig.shutdown();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}

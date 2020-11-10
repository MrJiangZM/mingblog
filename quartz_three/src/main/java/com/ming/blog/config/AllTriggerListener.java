package com.ming.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 4:51 下午
 */
@Slf4j
public class AllTriggerListener implements TriggerListener {

    private static final String TRIGGER_LISTENER = "TRIGGER_LISTENER";

    @Override
    public String getName() {
        return TRIGGER_LISTENER;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("quartz trigger misfired name:{}, group:{}",
                trigger.getKey().getName(), trigger.getKey().getGroup());
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
    }
}

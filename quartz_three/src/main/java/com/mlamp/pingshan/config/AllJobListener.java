package com...config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 4:26 下午
 */
@Slf4j
public class AllJobListener implements JobListener {

    private static final String JOB_LISTENER = "job_listener";

    @Override
    public String getName() {
        return JOB_LISTENER;
    }

    /**
     * 任务调用前
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
    }

    /**
     * 任务被拒绝
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        log.info("quartz job execute vetoed name:{}, group:{}",
                jobExecutionContext.getJobDetail().getKey().getName(),
                jobExecutionContext.getJobDetail().getKey().getGroup());
    }

    /**
     * 任务调度后
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
    }
}

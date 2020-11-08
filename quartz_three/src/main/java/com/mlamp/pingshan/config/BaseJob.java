package com...config;

import com...service.JobRecordService;
import com...service.SchedulerUtil;
import com...util.TraceId;
import org.quartz.*;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 5:56 下午
 */
public abstract class BaseJob implements Job {

    @Autowired
    private SchedulerUtil schedulerUtil;
    @Autowired
    private JobRecordService jobRecordService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            addJobId(jobExecutionContext);
//            countJob(jobExecutionContext);
            recordJobBefore(jobExecutionContext);
            doJob(jobExecutionContext);
            successJob(jobExecutionContext);
        } catch (Exception e) {
            jobError(jobExecutionContext);
        } finally {
            removeTraceId();
        }
    }

    private void countJob(JobExecutionContext jobExecutionContext) throws SchedulerException {
        Trigger trigger = jobExecutionContext.getTrigger();
        JobDataMap data = trigger.getJobDataMap();
        Object count = data.get("count");
        Integer num = null != count ? 0 : (Integer) count;
        data.put("count", ++num);
        schedulerUtil.resetTrigger(trigger, data);
    }

    private void removeTraceId() {
        MDC.remove("traceId");
    }

    private void jobError(JobExecutionContext jobExecutionContext) {
        jobRecordService.updateRecord(MDC.get("traceId"), "N");
    }

    private void successJob(JobExecutionContext jobExecutionContext) {
        jobRecordService.updateRecord(MDC.get("traceId"), "S");
    }

    private void recordJobBefore(JobExecutionContext jobExecutionContext) {
        jobRecordService.addRecord(jobExecutionContext);
    }

    private void addJobId(JobExecutionContext jobExecutionContext) {
        String requestId = TraceId.id();
        MDC.put("traceId", requestId);
    }

    public abstract void doJob(JobExecutionContext jobExecutionContext);

}

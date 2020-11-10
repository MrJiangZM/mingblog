package com.ming.blog.jobs;

import com.ming.blog.config.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:23 上午
 */
@Component("HelloJob")
@DisallowConcurrentExecution
public class HelloJob extends BaseJob {

    @Override
    public void doJob(JobExecutionContext jobExecutionContext) {
        System.out.println(new Date() + "测试simpleTrigger触发");
    }

}

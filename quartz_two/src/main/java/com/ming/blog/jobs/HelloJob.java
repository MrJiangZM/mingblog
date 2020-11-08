package com.ming.blog.jobs;

import com.ming.blog.config.BaseQuartzJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:23 上午
 */
@Component("HelloJob")
public class HelloJob implements BaseQuartzJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + "one job");
    }

}

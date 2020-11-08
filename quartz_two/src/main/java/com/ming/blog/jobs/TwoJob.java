package com.ming.blog.jobs;

import com.ming.blog.config.BaseQuartzJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 4:43 下午
 */
@Component("TwoJob")
public class TwoJob implements BaseQuartzJob {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + "two job");
    }

}

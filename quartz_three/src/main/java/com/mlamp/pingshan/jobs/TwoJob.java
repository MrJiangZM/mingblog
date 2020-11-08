package com...jobs;

import com...config.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 4:43 下午
 */
@Component("TwoJob")
@DisallowConcurrentExecution
public class TwoJob extends BaseJob {

//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println(new Date() + "two job");
//    }

    @Override
    public void doJob(JobExecutionContext jobExecutionContext) {
        System.out.println(new Date() + "two job");
    }

}

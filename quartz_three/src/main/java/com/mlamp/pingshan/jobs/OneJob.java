package com...jobs;

import com...config.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:23 上午
 */
@Slf4j
@Component("OneJob")
@DisallowConcurrentExecution
public class OneJob extends BaseJob {

    @Override
    public void doJob(JobExecutionContext jobExecutionContext) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "one job");
    }

}

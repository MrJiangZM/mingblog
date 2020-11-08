package com.ming.blog.tool;

import com.ming.blog.config.BaseQuartzJob;
import com.ming.blog.config.DateUnit;
import com.ming.blog.config.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.spi.MutableTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.quartz.DateBuilder.futureDate;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:38 上午
 */
@Slf4j
@Component
public class SchedulerUtil {

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    private DateUnit dateUnit;

    /**
     * 根据类名称，通过反射得到该类，然后创建一个BaseJob的实例。
     * 由于NewJob和HelloJob都实现了BaseJob，
     * 所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制
     * 2019 12/24更新不再使用反射，使用SpringUtil工具类；
     * 前提是具体的Job类必须交给Spring来管理;
     * 例如：HelloJob 在其类上加上@Component("helloJob")注解，并指定传入Bean的名称；
     *
     * @param classname
     *
     * @return
     *
     * @throws Exception
     */
    public BaseQuartzJob getClass(String classname) {
        BaseQuartzJob baseJob = (BaseQuartzJob) SpringUtil.getBean(classname);
        return baseJob;
    }

    public void addCronJob(JobInfo jobInfo) {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息addCronJob
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .withDescription(jobInfo.getDescription())
                    .storeDurably(true)
                    .build();

            scheduler.addJob(jobDetail, true);
            //表达式调度构建器(即任务执行的时间)
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
//            //按新的cronExpression表达式构建一个新的trigger
//            CronTrigger trigger = TriggerBuilder.newTrigger().
//                    withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
//                    .withSchedule(scheduleBuilder)
//                    .build();

//            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            System.out.println("创建定时任务失败" + e);
        }
    }


    public void addSimpleJob(JobInfo jobInfo) {
        try {
            // 启动调度器
            scheduler.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .build();

            DateBuilder.IntervalUnit verDate = dateUnit.verification(jobInfo.getTimeType());
            SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), verDate))
                    .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .build();

            scheduler.scheduleJob(jobDetail, simpleTrigger);

        } catch (Exception e) {
            System.out.println("创建定时任务失败" + e);
        }
    }

    public void pauseJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            System.out.println("暂停定时任务失败" + e);
        }
    }


    public void resumeJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            System.out.println("恢复定时任务失败" + e);
        }
    }

    public void updateJob(String jobClassName, String jobGroupName,
                          String description, List<TriggerInfo> triggerInfoList) {
        try {

            JobKey jobKey = JobKey.jobKey(jobClassName, jobGroupName);
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobKey)
                    .withDescription(description)
                    .build();
            scheduler.addJob(jobDetail, true, true);
//            scheduler.triggerJob(jobKey);
//            scheduler.
//            List<CoreTrigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey); TODO 是否需要删除原来的trigger？？
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            scheduler.pauseJob(jobKey);
            log.info("stop all");
            if (!CollectionUtils.isEmpty(triggerInfoList)) {
                for (TriggerInfo triggerInfo : triggerInfoList) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName());
                    Trigger triggerDB = scheduler.getTrigger(triggerKey);
                    // 按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, triggerDB);
                }
            }

//
//            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
//            // 表达式调度构建器
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//            // 按新的cronExpression表达式重新构建trigger
//            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//
//            // 按新的trigger重新设置job执行
//            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
        }
    }

    public void deleteJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
//            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            System.out.println("删除定时任务失败" + e);
        }

    }

    public void addTrigger(TriggerInfo triggerInfo) throws ParseException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(triggerInfo.getCornDescription());
        CronExpression cronExpression = new CronExpression(triggerInfo.getCornDescription());
        Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(new Date());
        MutableTrigger build1 = cronScheduleBuilder.build();
        Trigger build = TriggerBuilder.newTrigger().withDescription(triggerInfo.getDescription())
                .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName())
                .withSchedule(cronScheduleBuilder)
                .forJob(triggerInfo.getJobName(), triggerInfo.getJobGroupName())
                .build();
        try {
            scheduler.scheduleJob(build);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void addJob(JobInfo jobInfo) {
        try {

            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                    .withDescription(jobInfo.getDescription())
                    .storeDurably(true)
                    .build();
            scheduler.addJob(jobDetail, true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void pauseTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName());
//            Trigger trigger = scheduler.getTrigger(triggerKey);
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {

        }
    }

    public void resumeTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName());
//            Trigger trigger = scheduler.getTrigger(triggerKey);
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {

        }
    }

    public void deleteTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName());
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {

        }

    }

    public void updateTrigger(TriggerInfo triggerInfo) {
        try {

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(triggerInfo.getCornDescription());
            Trigger build = TriggerBuilder.newTrigger().withDescription(triggerInfo.getDescription())
                    .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName())
                    .withSchedule(cronScheduleBuilder)
                    .forJob(triggerInfo.getJobName(), triggerInfo.getJobGroupName())
                    .build();


            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroupName());

            scheduler.rescheduleJob(triggerKey, build);
//            scheduler.
        } catch (SchedulerException e) {

        }
    }
}

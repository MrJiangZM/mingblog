package com.ming.blog.service;

import com.ming.blog.config.BaseJob;
import com.ming.blog.config.DateUnit;
import com.ming.blog.config.SpringUtil;
import com.ming.blog.dao.JobAndTriggerDao;
import com.ming.blog.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.TriggerBuilder.newTrigger;

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
    @Autowired
    private JobAndTriggerDao jobAndTriggerDao;

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
    public BaseJob getClass(String classname) {
        BaseJob baseJob = (BaseJob) SpringUtil.getBean(classname);
        return baseJob;
    }

    public void addCronJob(JobInfo jobInfo) {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息addCronJob
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroup())
                    .withDescription(jobInfo.getDescription())
                    .storeDurably(true)
                    .build();

            scheduler.addJob(jobDetail, true);
            //表达式调度构建器(即任务执行的时间)
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
//            //按新的cronExpression表达式构建一个新的trigger
//            CronTrigger trigger = TriggerBuilder.newTrigger().
//                    withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroup())
//                    .withSchedule(scheduleBuilder)
//                    .build();

//            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            log.error("创建定时任务失败: {}" + e);
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS);
        }
    }


    public void addSimpleJob(JobInfo jobInfo) {
        try {
            // 启动调度器
            scheduler.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroup())
                    .build();

            DateBuilder.IntervalUnit verDate = dateUnit.verification(jobInfo.getTimeType());
            SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                    .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroup())
                    .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), verDate))
                    .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroup())
                    .build();

            scheduler.scheduleJob(jobDetail, simpleTrigger);

        } catch (Exception e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS);
        }
    }

    public void pauseJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "暂定定时任务失败");
        }
    }


    public void resumeJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "恢复定时任务失败");
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
//            List<CoreTrigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey); TODO 是否需要删除原来的trigger？？
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            scheduler.pauseJob(jobKey);
            log.info("stop all");
            if (!CollectionUtils.isEmpty(triggerInfoList)) {
                for (TriggerInfo triggerInfo : triggerInfoList) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
                    Trigger triggerDB = scheduler.getTrigger(triggerKey);
                    // 按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, triggerDB);
                }
            }
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
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "更新定时任务失败");
        }
    }

    public void deleteJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
//            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "删除定时任务失败");
        }

    }

    public void addTrigger(TriggerInfo triggerInfo) {
        if ("cron".equalsIgnoreCase(triggerInfo.getTriggerType())) {
            addCronTrigger(triggerInfo);
        } else if ("simple".equalsIgnoreCase(triggerInfo.getTriggerType())) {
            addSimpleTrigger(triggerInfo);
        } else {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
    }

    private void addCronTrigger(TriggerInfo triggerInfo) {
        try {
            //获取业务job类类型
//            Class cls = Class.forName(triggerInfo.getJobClassName());
//            JobDataMap data = new JobDataMap();
//            data.put("count", "0");
            //构建trigger
            TriggerKey triggerKey = TriggerKey.triggerKey(
                    "cron_" + triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(triggerInfo.getCronExpression());
            //构建job信息
            JobDetail jobDetail = scheduler.getJobDetail(
                    JobKey.jobKey(triggerInfo.getJobName(), triggerInfo.getJobGroup()));
            if (jobDetail == null) {
                jobDetail = JobBuilder.newJob(getClass(triggerInfo.getJobName()).getClass())
                        .withIdentity(triggerInfo.getJobName(), triggerInfo.getJobGroup())
                        .withDescription(triggerInfo.getDescription())
                        .build();
                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
//                    .usingJobData(data)
                        .withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                //构建trigger
                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .forJob(jobDetail)
//                    .usingJobData(data)
                        .withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(trigger);
            }
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "添加触发器失败");
        }
    }

    private void addSimpleTrigger(TriggerInfo triggerInfo) {
        try {
            //获取业务job类类型
            JobDataMap data = new JobDataMap();
            data.put("count", "0");

            //构建job信息
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(triggerInfo.getJobName(), triggerInfo.getJobGroup()));
            if (jobDetail == null) {
                jobDetail = JobBuilder.newJob(getClass(triggerInfo.getJobName()).getClass())
                        .withIdentity(triggerInfo.getJobName(), triggerInfo.getJobGroup())
                        .withDescription(triggerInfo.getDescription())
                        .build();
                SimpleTrigger trigger = newTrigger()
                        .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup())
                        .startAt(new Date(triggerInfo.getStartTime()))
                        .endAt(new Date(triggerInfo.getEndTime()))
                        .usingJobData(data)
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(triggerInfo.getInterval())
                                .withRepeatCount(triggerInfo.getRepeatCount())
                        ).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                SimpleTrigger trigger = newTrigger()
                        .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup())
                        .startAt(new Date(triggerInfo.getStartTime()))
                        .endAt(new Date(triggerInfo.getEndTime()))
                        .usingJobData(data)
                        .forJob(jobDetail)
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(triggerInfo.getInterval())
                                .withRepeatCount(triggerInfo.getRepeatCount())
                        ).build();
                scheduler.scheduleJob(trigger);
            }
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "添加触发器失败");
        }
    }

    public void addJob(JobInfo jobInfo) {
        try {

            JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobName()).getClass())
                    .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
                    .withDescription(jobInfo.getDescription())
                    .storeDurably(true)
                    .build();
            scheduler.addJob(jobDetail, true);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "添加任务器失败");
        }
    }

    public void pauseTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
//            Trigger trigger = scheduler.getTrigger(triggerKey);
            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "暂停触发器失败");
        }
    }

    public void resumeTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
//            Trigger trigger = scheduler.getTrigger(triggerKey);
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "恢复触发器失败");
        }
    }

    public void deleteTrigger(TriggerInfo triggerInfo) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "删除触发器失败");
        }

    }

    public void updateTrigger(TriggerInfo triggerInfo) {
        try {
            Trigger trigger;
            if ("cron".equalsIgnoreCase(triggerInfo.getTriggerType())) {
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(triggerInfo.getCronExpression());
                trigger = newTrigger().withDescription(triggerInfo.getDescription())
                        .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup())
                        .withSchedule(cronScheduleBuilder)
                        .forJob(triggerInfo.getJobName(), triggerInfo.getJobGroup())
                        .build();
            } else if ("simple".equalsIgnoreCase(triggerInfo.getTriggerType())) {
                trigger = newTrigger()
                        .withIdentity(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup())
                        .startAt(new Date(triggerInfo.getStartTime()))
                        .endAt(new Date(triggerInfo.getEndTime()))
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(triggerInfo.getInterval())
                                .withRepeatCount(triggerInfo.getRepeatCount())
                        ).build();
            } else {
                throw new ServerException(StatusEnum.PARAM_ERROR);
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerInfo.getTriggerName(), triggerInfo.getTriggerGroup());
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "更新触发器失败");
        }
    }

    public void resetTrigger(Trigger trigger, JobDataMap data) throws SchedulerException {
//        scheduler.scheduleJob(trigger);
    }

    public void addInfo(List<QuartzTrigger> list) throws SchedulerException {
        if (!CollectionUtils.isEmpty(list)) {
            for (QuartzTrigger trigger : list) {
                Trigger triggerDB = scheduler.getTrigger(
                        TriggerKey.triggerKey(trigger.getTriggerName(), trigger.getTriggerGroup()));
                if (StringUtils.equalsIgnoreCase("cron", trigger.getTriggerType())) {
                    CronTriggerInfo cronTrigger = jobAndTriggerDao
                            .getCronTrigger(trigger.getTriggerName(), trigger.getTriggerGroup());
                    trigger.setCron(cronTrigger.getCronExpression());
                } else if (StringUtils.equalsIgnoreCase("simple", trigger.getTriggerType())) {
                    trigger.setStartTime(triggerDB.getStartTime().getTime());
                    trigger.setEndTime(triggerDB.getEndTime().getTime());
                    SimpleTriggerInfo simpleTrigger = jobAndTriggerDao
                            .getSimpleTrigger(trigger.getTriggerName(), trigger.getTriggerGroup());
                    trigger.setRepeatCount(simpleTrigger.getRepeatCount());
                    trigger.setRepeatInterval(simpleTrigger.getRepeatInterval());

                }
            }
        }
    }
}

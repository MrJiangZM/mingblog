package com.ming.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ming.blog.service.JobAndTriggerService;
import com.ming.blog.tool.JobAndTrigger;
import com.ming.blog.tool.JobInfo;
import com.ming.blog.tool.SchedulerUtil;
import com.ming.blog.tool.TriggerInfo;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:33 上午
 */
@RestController
@RequestMapping("/quartz")
public class JobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    private SchedulerUtil schedulerUtil;

    /**
     * 添加任务
     *
     * @param jobInfo
     *
     * @throws Exception
     */
    @PostMapping(value = "/job/add")
    public void addjob(@RequestBody JobInfo jobInfo) {
        if ("".equals(jobInfo.getJobClassName()) || "".equals(jobInfo.getJobGroupName()) || "".equals(jobInfo.getCronExpression())) {
            return;
        }
        schedulerUtil.addJob(jobInfo);
    }

    /**
     * 暂停
     */
    @PostMapping("/job/pause")
    public void pauseJob(@RequestBody JobInfo jobInfo) {
        String jobClassName = jobInfo.getJobClassName();
        String jobGroupName = jobInfo.getJobGroupName();
        schedulerUtil.pauseJob(jobClassName, jobGroupName);
    }

    @PostMapping(value = "/job/resume")
    public void resumejob(@RequestBody JobInfo jobInfo) {
        String jobClassName = jobInfo.getJobClassName();
        String jobGroupName = jobInfo.getJobGroupName();
        schedulerUtil.resumeJob(jobClassName, jobGroupName);
    }

    @PostMapping(value = "/job/update")
    public void updateJob(@RequestBody JobInfo jobInfo) {
        String jobClassName = jobInfo.getJobClassName();
        String jobGroupName = jobInfo.getJobGroupName();
        String description = jobInfo.getDescription();
        schedulerUtil.updateJob(jobClassName, jobGroupName, description, jobInfo.getTriggerInfoList());
    }

    /**
     * 删除任务
     * 删除操作前应该暂停该任务的触发器，并且停止该任务的执行
     *
     * @throws Exception
     */
    @PostMapping(value = "/job/delete")
    public void deletejob(@RequestBody JobInfo jobInfo) {
        String jobClassName = jobInfo.getJobClassName();
        String jobGroupName = jobInfo.getJobGroupName();
        schedulerUtil.deleteJob(jobClassName, jobGroupName);
    }

    @GetMapping(value = "/job/list")
    public Map<String, Object> listJob(@RequestParam(value = "description") String description,
                                       @RequestParam(value = "pageNum") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.pageJob(description, pageNum, pageSize);
        return null;
    }

    /**
     * 添加任务
     *
     * @param
     *
     * @throws Exception
     */
    @PostMapping(value = "/trigger/add")
    public void addTrigger(@RequestBody TriggerInfo triggerInfo) throws ParseException {
        if ("".equals(triggerInfo.getTriggerName())
                || "".equals(triggerInfo.getTriggerGroupName())) {
            return;
        }
        schedulerUtil.addTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/pause")
    public void pauseTrigger(@RequestBody TriggerInfo triggerInfo) {
        if ("".equals(triggerInfo.getTriggerName())
                || "".equals(triggerInfo.getTriggerGroupName())) {
            return;
        }
        schedulerUtil.pauseTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/resume")
    public void resumeTrigger(@RequestBody TriggerInfo triggerInfo) {
        if ("".equals(triggerInfo.getTriggerName())
                || "".equals(triggerInfo.getTriggerGroupName())) {
            return;
        }
        schedulerUtil.resumeTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/delete")
    public void deleteTrigger(@RequestBody TriggerInfo triggerInfo) {
        if ("".equals(triggerInfo.getTriggerName())
                || "".equals(triggerInfo.getTriggerGroupName())) {
            return;
        }
        schedulerUtil.deleteTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/update")
    public void updateTrigger(@RequestBody TriggerInfo triggerInfo) {
        if ("".equals(triggerInfo.getTriggerName())
                || "".equals(triggerInfo.getTriggerGroupName())) {
            return;
        }
        schedulerUtil.updateTrigger(triggerInfo);
    }

}

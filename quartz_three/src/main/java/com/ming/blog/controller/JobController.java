package com.ming.blog.controller

...controller;

import com.google.common.collect.Maps;
...exception.ServerException;
import com...exception.StatusEnum;
...pojo.JobInfo;
...pojo.TriggerInfo;
...service.JobAndTriggerService;
...service.SchedulerUtil;
...util.PageHelper;
import com.ming.blog.pojo.JobInfo;
import com.ming.blog.pojo.TriggerInfo;
import com.ming.blog.service.JobAndTriggerService;
import com.ming.blog.service.SchedulerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 10:53 上午
 */
@RestController
//@MonitoredWithSpring
@RequestMapping("/quartz")
public class JobController {

    @Autowired
    private JobAndTriggerService jobAndTriggerService;
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
//        if (StringUtils.isEmpty(jobInfo.getJobName()) || StringUtils.isEmpty(jobInfo.getJobGroup())) {
//            throw new ServerException(RestResponseStatus.PARAM_ERROR);
//        }
        schedulerUtil.addJob(jobInfo);
    }

    /**
     * 暂停
     */
    @PostMapping("/job/pause")
    public void pauseJob(@RequestBody JobInfo jobInfo) {
        if (StringUtils.isEmpty(jobInfo.getJobName()) || StringUtils.isEmpty(jobInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.pauseJob(jobInfo.getJobName(), jobInfo.getJobGroup());
    }

    @PostMapping(value = "/job/resume")
    public void resumejob(@RequestBody JobInfo jobInfo) {
        if (StringUtils.isEmpty(jobInfo.getJobName()) || StringUtils.isEmpty(jobInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.resumeJob(jobInfo.getJobName(), jobInfo.getJobGroup());
    }

    @PostMapping(value = "/job/update")
    public void updateJob(@RequestBody JobInfo jobInfo) {
        if (StringUtils.isEmpty(jobInfo.getJobName()) || StringUtils.isEmpty(jobInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.updateJob(jobInfo.getJobName(), jobInfo.getJobGroup(),
                jobInfo.getDescription(), jobInfo.getTriggerInfoList());
    }

    /**
     * 删除任务
     * 删除操作前应该暂停该任务的触发器，并且停止该任务的执行
     *
     * @throws Exception
     */
    @PostMapping(value = "/job/delete")
    public void deletejob(@RequestBody JobInfo jobInfo) {
        if (StringUtils.isEmpty(jobInfo.getJobName()) || StringUtils.isEmpty(jobInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.deleteJob(jobInfo.getJobName(), jobInfo.getJobGroup());
    }

    @GetMapping(value = "/job/list")
    public PageHelper listJob(@RequestParam(value = "jobName", required = false) String jobName,
                              @RequestParam(value = "jobGroup", required = false) String jobGroup,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return jobAndTriggerService.pageJob(jobName, jobGroup, description, currentPage, pageSize);
    }

    @GetMapping(value = "/job/grouplist")
    public Map groupList() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("groupList", jobAndTriggerService.jobGroupList());
        return map;
    }

    /**
     * 添加任务
     *
     * @param
     */
    @PostMapping(value = "/trigger/add")
    public void addTrigger(@RequestBody TriggerInfo triggerInfo) {
        if (StringUtils.isEmpty(triggerInfo.getTriggerName()) || StringUtils.isEmpty(triggerInfo.getTriggerGroup())
                || StringUtils.isEmpty(triggerInfo.getJobName()) || StringUtils.isEmpty(triggerInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.addTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/pause")
    public void pauseTrigger(@RequestBody TriggerInfo triggerInfo) {
        if (StringUtils.isEmpty(triggerInfo.getTriggerName()) || StringUtils.isEmpty(triggerInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.pauseTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/resume")
    public void resumeTrigger(@RequestBody TriggerInfo triggerInfo) {
        if (StringUtils.isEmpty(triggerInfo.getTriggerName()) || StringUtils.isEmpty(triggerInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.resumeTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/delete")
    public void deleteTrigger(@RequestBody TriggerInfo triggerInfo) {
        if (StringUtils.isEmpty(triggerInfo.getTriggerName()) || StringUtils.isEmpty(triggerInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.deleteTrigger(triggerInfo);
    }

    @PostMapping(value = "/trigger/update")
    public void updateTrigger(@RequestBody TriggerInfo triggerInfo) {
        if (StringUtils.isEmpty(triggerInfo.getTriggerName()) || StringUtils.isEmpty(triggerInfo.getJobGroup())) {
            throw new ServerException(StatusEnum.PARAM_ERROR);
        }
        schedulerUtil.updateTrigger(triggerInfo);
    }

    @GetMapping(value = "/trigger/list")
    public PageHelper pageTrigger(@RequestParam(value = "jobGroup", required = false) String jobGroup,
                                  @RequestParam(value = "description", required = false) String description,
                                  @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return jobAndTriggerService.pageTrigger(jobGroup, description, currentPage, pageSize);
    }

    @GetMapping(value = "/trigger/grouplist")
    public Map triggerGroupList() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("groupList", jobAndTriggerService.triggerGroupList());
        return map;
    }

}

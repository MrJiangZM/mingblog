package com.ming.blog.service;
import com.ming.blog.pojo.JobAndTrigger;
import com.ming.blog.pojo.QuartzJob;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:35 上午
 */
public interface JobAndTriggerService {

    PageHelper<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

    PageHelper<QuartzJob> pageJob(String jobName, String jobGroupName,
                                  String description, Integer pageNum, Integer pageSize);

    List<String> jobGroupList();

    PageHelper pageTrigger(String jobGroupName, String description, Integer pageNum, Integer pageSize);

    List<String> triggerGroupList();
}

package com.ming.blog.service;

import com.github.pagehelper.PageInfo;
import com.ming.blog.tool.JobAndTrigger;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:35 上午
 */
public interface JobAndTriggerService {

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

    PageInfo<JobAndTrigger> pageJob(String description, Integer pageNum, Integer pageSize);
}

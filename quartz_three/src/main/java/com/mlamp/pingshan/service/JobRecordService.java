package com...service;

import com...pojo.JobRecord;
import com...util.PageHelper;
import org.quartz.JobExecutionContext;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 6:42 下午
 */
public interface JobRecordService {

    void addRecord(JobExecutionContext jobExecutionContext);

    void updateRecord(String traceId, String status);

    PageHelper<JobRecord> page(String jobName, Long startTime,
                               Long endTime, Integer currentPage, Integer pageSize);
}

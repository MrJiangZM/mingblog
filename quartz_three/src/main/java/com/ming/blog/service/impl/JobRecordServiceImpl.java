package com.ming.blog.service.impl

...service.impl;

...dao.JobRecordDao;
import com...pojo.JobRecord;
...service.JobRecordService;
...util.PageHelper;
import com.ming.blog.dao.JobRecordDao;
import com.ming.blog.pojo.JobRecord;
import com.ming.blog.service.JobRecordService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 6:42 下午
 */
@Service
public class JobRecordServiceImpl implements JobRecordService {

    @Autowired
    private JobRecordDao jobRecordDao;

    @Override
    public void addRecord(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String traceId = MDC.get("traceId");
        JobRecord record = JobRecord.builder().startTime(System.currentTimeMillis())
                .jobDescription(jobDetail.getDescription())
                .jobName(jobDetail.getKey().getName())
                .jobGroup(jobDetail.getKey().getGroup())
                .triggerDescription(jobExecutionContext.getTrigger().getDescription())
                .triggerName(jobExecutionContext.getTrigger().getKey().getName())
                .triggerGroup(jobExecutionContext.getTrigger().getKey().getGroup())
                .traceId(traceId)
                .result("I").build();
        jobRecordDao.save(record);

    }

    @Override
    public void updateRecord(String traceId, String status) {
        JobRecord byTraceId = jobRecordDao.findByTraceId(traceId);
        if (null != byTraceId) {
            byTraceId.setResult(status);
            byTraceId.setEndTime(System.currentTimeMillis());
            jobRecordDao.save(byTraceId);
        }
    }

    @Override
    public PageHelper<JobRecord> page(String jobName, Long startTime, Long endTime,
                                      Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<JobRecord> pageData = jobRecordDao.page(jobName, startTime, endTime, pageRequest);
        Long totalElements = pageData.getTotalElements();
        PageHelper<JobRecord> PageHelper = new PageHelper<>(totalElements.intValue(), currentPage, pageSize);
        PageHelper.setData(pageData.getContent());
        return PageHelper;
    }

}

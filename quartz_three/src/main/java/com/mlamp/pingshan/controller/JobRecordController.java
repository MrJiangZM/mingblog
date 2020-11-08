package com...controller;

import com...pojo.JobRecord;
import com...service.JobRecordService;
import com...util.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Zaiming
 * @date 2020/3/26 9:43 上午
 */
@Slf4j
@RestController
@RequestMapping("/record")
public class JobRecordController {

    @Autowired
    private JobRecordService jobRecordService;

    @GetMapping("/list")
    public PageHelper<JobRecord> page(@RequestParam(value = "jobName", required = false) String jobName,
                                      @RequestParam(value = "startTime", required = false) Long startTime,
                                      @RequestParam(value = "endTime", required = false) Long endTime,
                                      @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return jobRecordService.page(jobName, startTime, endTime, currentPage, pageSize);
    }

}

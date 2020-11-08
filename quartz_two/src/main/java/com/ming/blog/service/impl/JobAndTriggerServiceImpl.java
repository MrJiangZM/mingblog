package com.ming.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.blog.dao.JobAndTriggerDao;
import com.ming.blog.dao.JobAndTriggerJdbcDao;
import com.ming.blog.service.JobAndTriggerService;
import com.ming.blog.tool.JobAndTrigger;
import com.ming.blog.tool.QuartzJob;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 11:59 上午
 */
@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService {

    @Autowired
    private JobAndTriggerJdbcDao jobAndTriggerDao;

    @Override
    public PageInfo<JobAndTrigger>  getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        List<JobAndTrigger> list = jobAndTriggerDao.getJobAndTriggerDetails(pageNum, pageSize);
        Integer totalRow = jobAndTriggerDao.getTotalRows();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<JobAndTrigger> pageJob(String description, Integer pageNum, Integer pageSize) {
        List<QuartzJob> list = jobAndTriggerDao.list(description, pageNum, pageSize);
        Integer count = jobAndTriggerDao.count(description);
        return null;
    }

}

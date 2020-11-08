package com...service.impl;

import com...dao.JobAndTriggerDao;
import com...exception.ServerException;
import com...exception.StatusEnum;
import com...pojo.JobAndTrigger;
import com...pojo.QuartzJob;
import com...pojo.QuartzTrigger;
import com...service.JobAndTriggerService;
import com...service.SchedulerUtil;
import com...util.PageHelper;
import org.quartz.SchedulerException;
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
    private JobAndTriggerDao jobAndTriggerDao;
    @Autowired
    private SchedulerUtil schedulerUtil;

    @Override
    public PageHelper<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        List<JobAndTrigger> list = jobAndTriggerDao.getJobAndTriggerDetails(pageNum, pageSize);
        Integer totalRow = jobAndTriggerDao.getTotalRows();
        PageHelper<JobAndTrigger> page = new PageHelper<>(totalRow, pageNum, pageSize);
        page.setData(list);
        return page;
    }

    @Override
    public PageHelper<QuartzJob> pageJob(String jobName, String jobGroupName, String description, Integer pageNum, Integer pageSize) {
        Integer count = jobAndTriggerDao.count(jobName, jobGroupName, description);
        PageHelper<QuartzJob> page = new PageHelper<>(count, pageNum, pageSize);
        if (count < 1) {
            return page;
        }
        List<QuartzJob> list = jobAndTriggerDao.list(jobName, jobGroupName, description, pageNum, pageSize);
        page.setData(list);
        return page;
    }

    @Override
    public List<String> jobGroupList() {
        return jobAndTriggerDao.jobGroupList();
    }

    @Override
    public PageHelper pageTrigger(String jobGroupName, String description, Integer pageNum, Integer pageSize) {
        Integer count = jobAndTriggerDao.countTrigger(jobGroupName, description);
        PageHelper<QuartzTrigger> page = new PageHelper<>(count, pageNum, pageSize);
        if (count < 1) {
            return page;
        }
        List<QuartzTrigger> list = jobAndTriggerDao.listTrigger(jobGroupName, description, pageNum, pageSize);
        try {
            schedulerUtil.addInfo(list);
        } catch (SchedulerException e) {
            throw new ServerException(StatusEnum.COMMON_ERROR_STATUS, "查找触发器相关信息失败");
        }
        page.setData(list);
        return page;
    }

    @Override
    public List<String> triggerGroupList() {
        return jobAndTriggerDao.triggerGroupList();
    }


}

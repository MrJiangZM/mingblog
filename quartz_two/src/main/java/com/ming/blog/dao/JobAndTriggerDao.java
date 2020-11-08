package com.ming.blog.dao;

import com.ming.blog.tool.JobAndTrigger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 12:01 下午
 */
@Repository
public interface JobAndTriggerDao {

    List<JobAndTrigger> getJobAndTriggerDetails();

}

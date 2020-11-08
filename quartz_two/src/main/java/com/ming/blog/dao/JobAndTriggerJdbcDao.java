package com.ming.blog.dao;

import com.ming.blog.tool.JobAndTrigger;
import com.ming.blog.tool.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/3/24 12:35 下午
 */
@Slf4j
@Repository
public class JobAndTriggerJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<JobAndTrigger> getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        String sql = "SELECT QRTZ_JOB_DETAILS.JOB_NAME as jobName , " +
                "QRTZ_JOB_DETAILS.JOB_GROUP as jobGroup , " +
                "QRTZ_JOB_DETAILS.JOB_CLASS_NAME as jobClassName , " +
                "QRTZ_TRIGGERS.TRIGGER_NAME as triggerName , " +
                "QRTZ_JOB_DETAILS.DESCRIPTION as description , " +
                "QRTZ_TRIGGERS.TRIGGER_GROUP as triggerGroup , " +
                "QRTZ_TRIGGERS.TRIGGER_STATE as triggerState , " +
                "QRTZ_CRON_TRIGGERS.CRON_EXPRESSION as cornExpression , " +
                "QRTZ_CRON_TRIGGERS.TIME_ZONE_ID as timeZoneId " +
                "FROM " +
                "QRTZ_JOB_DETAILS " +
                "LEFT JOIN QRTZ_TRIGGERS ON QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_JOB_DETAILS.JOB_GROUP " +
                "LEFT JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
                "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
                "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP limit ?, ?";
        List<JobAndTrigger> list = null;
        Object[] args = {(pageNum - 1) * pageSize, pageSize};
        try {
            list = jdbcTemplate.query(sql, args,
                    new BeanPropertyRowMapper<>(JobAndTrigger.class));
        } catch (DataAccessException e) {
            log.error(sql, e);
        }
        return list;
    }

    public Integer getTotalRows() {
        String sql = "SELECT COUNT(1) " +
                "FROM " +
                "QRTZ_JOB_DETAILS " +
                "LEFT JOIN QRTZ_TRIGGERS ON QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_JOB_DETAILS.JOB_GROUP " +
                "LEFT JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
                "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
                "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<QuartzJob> list(String description, Integer pageNum, Integer pageSize) {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "D.JOB_NAME as jobName , " +
                "D.JOB_GROUP as jobGroup , " +
                "D.JOB_CLASS_NAME as jobClassName ," +
                "D.DESCRIPTION as description ," +
                "D.IS_DURABLE as isDurable" +
                "FROM " +
                "QRTZ_JOB_DETAILS D ");
        if (StringUtils.isNotEmpty(description)) {
            sb.append("where D.DESCRIPTION like ");
            sb.append("%" + description +"%");
        }
        sb.append("limit ?, ?");
        Object[] args = {(pageNum - 1) * pageSize, pageSize};
        return jdbcTemplate.query(sb.toString(), args, new BeanPropertyRowMapper<>(QuartzJob.class));
    }

    public Integer count(String description) {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "D.JOB_NAME as jobName , " +
                "D.JOB_GROUP as jobGroup , " +
                "D.JOB_CLASS_NAME as jobClassName ," +
                "D.DESCRIPTION as description ," +
                "D.IS_DURABLE as isDurable" +
                "FROM " +
                "QRTZ_JOB_DETAILS D ");
        if (StringUtils.isNotEmpty(description)) {
            sb.append("where D.DESCRIPTION like ");
            sb.append("%" + description +"%");
        }
        return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
    }

}

package com.ming.blog.dao

...dao;

import com.beust.jcommander.internal.Lists;
import com...pojo.*;
import com.ming.blog.pojo.*;
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
public class JobAndTriggerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SimpleTriggerInfo getSimpleTrigger(String triggerName, String triggerGroup) {
        String sql = "SELECT CT.TRIGGER_NAME AS triggerName, " +
                "CT.TRIGGER_GROUP AS triggerGroup, " +
                "CT.REPEAT_COUNT AS repeatCount, " +
                "CT.REPEAT_INTERVAL AS repeatInterval, " +
                "CT.TIMES_TRIGGERED AS timesTriggered " +
                "from QRTZ_SIMPLE_TRIGGERS CT " +
                "where CT.TRIGGER_NAME = ? and CT.TRIGGER_GROUP = ? ";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(SimpleTriggerInfo.class),  triggerName, triggerGroup);
    }

    public CronTriggerInfo getCronTrigger(String triggerName, String triggerGroup) {
        String sql = "SELECT CT.TRIGGER_NAME AS triggerName, " +
                "CT.TRIGGER_GROUP AS triggerGroup, " +
                "CT.CRON_EXPRESSION AS cronExpression, " +
                "CT.TIME_ZONE_ID AS timeZoneId   " +
                "from QRTZ_CRON_TRIGGERS CT " +
                "where CT.TRIGGER_NAME = ? and CT.TRIGGER_GROUP = ? ";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(CronTriggerInfo.class),  triggerName, triggerGroup);
    }

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

    public List<QuartzJob> list(String jobName, String jobGroupName,
                                String description, Integer pageNum, Integer pageSize) {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "D.JOB_NAME as jobName , " +
                "D.JOB_GROUP as jobGroup , " +
                "D.JOB_CLASS_NAME as jobClassName ," +
                "D.DESCRIPTION as description ," +
                "D.IS_DURABLE as isDurable " +
                "FROM " +
                "QRTZ_JOB_DETAILS D " +
                "where 1=1 ");
        List<Object> args = Lists.newArrayList();
        if (StringUtils.isNotEmpty(description)) {
            sb.append("and D.DESCRIPTION like ");
            sb.append("'%" + description +"%' ");
        }
        if (StringUtils.isNotEmpty(jobName)) {
            sb.append("and D.JOB_NAME = ?  ");
            args.add(jobName);
        }
        if (StringUtils.isNotEmpty(jobGroupName)) {
            sb.append("and D.JOB_GROUP = ?  ");
            args.add(jobGroupName);
        }
        sb.append("limit ?, ?");
        args.add((pageNum - 1) * pageSize);
        args.add(pageSize);
        return jdbcTemplate.query(sb.toString(), args.toArray(), new BeanPropertyRowMapper<>(QuartzJob.class));
    }

    public Integer count(String jobName, String jobGroupName, String description) {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "count(1) " +
                "from " +
                "QRTZ_JOB_DETAILS D " +
                "where 1=1 ");
        List<Object> args = Lists.newArrayList();
        if (StringUtils.isNotEmpty(description)) {
            sb.append("and D.DESCRIPTION like ");
            sb.append("'%" + description +"%'  ");
        }
        if (StringUtils.isNotEmpty(jobName)) {
            sb.append("and D.JOB_NAME = ?  ");
            args.add(jobName);
        }
        if (StringUtils.isNotEmpty(jobGroupName)) {
            sb.append("and D.JOB_GROUP = ?  ");
            args.add(jobGroupName);
        }
        return jdbcTemplate.queryForObject(sb.toString(), args.toArray(), Integer.class);
    }

    public List<String> jobGroupList() {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "distinct D.JOB_GROUP " +
                "FROM " +
                "QRTZ_JOB_DETAILS D ");
        return jdbcTemplate.queryForList(sb.toString(), String.class);
    }

    public List<QuartzTrigger> listTrigger(String jobGroupName, String description,
                                           Integer pageNum, Integer pageSize) {
        StringBuilder sb = new StringBuilder("SELECT " +
//                "t.SCHED_NAME AS schedName, " +
                "t.TRIGGER_NAME AS triggerName, " +
                "t.TRIGGER_GROUP AS triggerGroup, " +
                "t.DESCRIPTION AS description, " +
                "t.NEXT_FIRE_TIME AS nextFireTime, " +
                "PREV_FIRE_TIME AS prevFireTime, " +
//                "PRIORITY AS proprity, " +
                "TRIGGER_STATE AS triggerState, " +
                "TRIGGER_TYPE AS triggerType, " +
                "START_TIME AS startTime, " +
                "END_TIME AS endTime, " +
//                "CALENDAR_NAME AS calendarName, " +
                "MISFIRE_INSTR AS misfireInstr, " +
//                "JOB_DATA AS jobData " +
                "JOB_NAME AS jobName, "+
                "JOB_GROUP AS jobGroup "+
                "FROM " +
                "QRTZ_TRIGGERS t " +
                "where 1=1 ");
        List<Object> args = Lists.newArrayList();
        if (StringUtils.isNotEmpty(description)) {
            sb.append("and t.DESCRIPTION like ");
            sb.append("'%" + description +"%' ");
        }

        if (StringUtils.isNotEmpty(jobGroupName)) {
            sb.append("and D.TRIGGER_GROUP = ?  ");
            args.add(jobGroupName);
        }
        sb.append("limit ?, ?");
        args.add((pageNum - 1) * pageSize);
        args.add(pageSize);
        return jdbcTemplate.query(sb.toString(), args.toArray(), new BeanPropertyRowMapper<>(QuartzTrigger.class));
    }

    public Integer countTrigger(String jobGroupName, String description) {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "count(1) " +
                "from "+
                "QRTZ_TRIGGERS T " +
                "where 1=1 ");
        List<Object> args = Lists.newArrayList();
        if (StringUtils.isNotEmpty(description)) {
            sb.append("and T.DESCRIPTION like ");
            sb.append("'%" + description +"%' ");
        }
        if (StringUtils.isNotEmpty(jobGroupName)) {
            sb.append("and D.TRIGGER_GROUP = ?  ");
            args.add(jobGroupName);
        }
        return jdbcTemplate.queryForObject(sb.toString(), args.toArray(), Integer.class);
    }

    public List<String> triggerGroupList() {
        StringBuilder sb = new StringBuilder("SELECT\n" +
                "distinct T.TRIGGER_GROUP " +
                "FROM " +
                "QRTZ_TRIGGERS T ");
        return jdbcTemplate.queryForList(sb.toString(), String.class);
    }
}

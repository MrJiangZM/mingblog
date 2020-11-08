package com...dao;

import com...pojo.JobRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 5:33 下午
 */
@Repository
public interface JobRecordDao extends JpaRepository<JobRecord, Integer> {

    @Query(nativeQuery = true,
            value = "select * from job_record where trace_id = ?1")
    JobRecord findByTraceId(String traceId);

    @Query(nativeQuery = true,
            value = "select * from job_record where 1=1 " +
                    "and if(?1 != '', job_name = ?1, 1=1) " +
                    "and if(?2 != '', start_time >= ?2, 1=1) " +
                    "and if(?3 != '', end_time <= ?3, 1=1) order by start_time desc")
    Page<JobRecord> page(String jobName, Long startTime, Long endTime, PageRequest pageRequest);
}

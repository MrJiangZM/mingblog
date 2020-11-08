package com.ming.blog.service;

import com.ming.blog.dao.StudentLogDao;
import com.ming.blog.domain.StudentLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentLogService {

    @Autowired
    private StudentLogDao studentLogDao;

    public Object findAll() {
        List<StudentLog> all = studentLogDao.findAll();
        System.out.println(all);
        log.info("try to get list:{}", all);
        return all;
    }

}

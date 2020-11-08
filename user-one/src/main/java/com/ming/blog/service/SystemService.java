package com.ming.blog.service;

import com.ming.blog.dao.SystemDao;
import com.ming.blog.domain.SysSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Zaiming
 * @date 2020/4/3 11:43 上午
 */
@Service
public class SystemService {

    @Autowired
    private SystemDao systemDao;


    public void add(SysSystem system) {
        systemDao.save(system);
    }

    public SysSystem find(Long id) {
        return systemDao.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public void delete(Long id) {
        SysSystem system = find(id);
        systemDao.delete(system);
    }

    public List<SysSystem> list() {
        List<SysSystem> all = systemDao.findAll();
        return all;
    }
}

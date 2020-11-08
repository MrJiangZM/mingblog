package com.ming.blog.external;

import com.ming.blog.dao.ESPersonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonESTemplate extends ESBaseTemplate {

    @Autowired
    private ESPersonDao esPersonDao;



}

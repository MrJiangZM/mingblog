package com.ming.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    /**
     * 加载配置文件中的数据源基本数据
     * @return
     */
    @Bean("primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DruidDataSource primaryDataSourceProperties() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.addFilters("stat,wall,log4j");
        return druidDataSource;
//        DruidDataSource dataSource = DruidDataSourceBuilder
//                .create().build(environment, "spring.datasource.druid.primary.");
//        dataSource.addFilters("stat,wall,log4j");
//        return dataSource;
    }

    /**
     * 加载配置文件中的数据源基本数据
     * @return
     */
    @Bean("secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.secondary")
    public DruidDataSource secondaryDataSourceProperties() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.addFilters("stat,wall,log4j");
        return druidDataSource;
//        DruidDataSource dataSource = DruidDataSourceBuilder
//                .create().build(environment, "spring.datasource.druid.secondary.");
//        dataSource.addFilters("stat,wall,log4j");
//        return dataSource;
    }

}

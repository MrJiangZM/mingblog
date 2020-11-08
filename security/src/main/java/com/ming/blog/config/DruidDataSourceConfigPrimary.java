package com.ming.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.ming.blog.dao.primary"},
            sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DruidDataSourceConfigPrimary {

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
    }

    /**
     * 加载template类
     * @return
     * @throws Exception
     */
    @Bean("primaryTransactionManager")
    @Qualifier("primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    /**
     * 加载sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("primarySqlSessionFactory")
    @Qualifier("primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/primary/*.xml"));
        return factoryBean.getObject();
    }

    /**
     * 加载template类
     * @return
     * @throws Exception
     */
    @Bean("primarySqlSessionTemplate")
    @Qualifier("primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

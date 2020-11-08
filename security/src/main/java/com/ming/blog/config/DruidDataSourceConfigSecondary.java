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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.ming.blog.dao.secondary"},
            sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class DruidDataSourceConfigSecondary {

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
    }

    /**
     * 加载template类
     * @return
     * @throws Exception
     */
    @Bean("secondaryTransactionManager")
    @Qualifier("secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    /**
     * 加载sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("secondarySqlSessionFactory")
    @Qualifier("secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/secondary/*.xml"));
        return factoryBean.getObject();
    }

    /**
     * 加载template类
     * @return
     * @throws Exception
     */
    @Bean("secondarySqlSessionTemplate")
    @Qualifier("secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

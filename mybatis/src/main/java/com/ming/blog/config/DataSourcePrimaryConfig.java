package com.ming.blog.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * primary必须添加，指定某人的数据源信息
 * bean 添加到容器中
 *
 * mapperScan  basePackages 扫描的dao包路劲， sqlSessionTemplateRef 指定jdbcTemplate
 */
@Slf4j
//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = {"com.ming.blog.dao.primary"},
//            sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DataSourcePrimaryConfig {

    /**
     * 加载配置文件中的数据源基本数据
     * @return
     */
    @Bean("primaryDataSourceProperties")
    @Qualifier("primaryDataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 加载数据源的配置信息
     * @return
     */
    @Bean("primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.primary.configuration")
    public DataSource primaryDataSource() {
        return primaryDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)// 3. 可以显示指定连接池，也可以不显示指定；即此行代码可以注释掉
                .build();
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

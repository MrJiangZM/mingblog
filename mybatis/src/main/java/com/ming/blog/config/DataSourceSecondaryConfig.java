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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
//@EnableTransactionManagement
//@Configuration
//@MapperScan(basePackages = {"com.ming.blog.dao.secondary"},
//        sqlSessionTemplateRef = "secondarySqlSessionTemplate")
public class DataSourceSecondaryConfig {

    /**
     * 加载配置文件中的数据源基本数据
     * @return
     */
    @Bean("secondaryDataSourceProperties")
    @Qualifier("secondaryDataSourceProperties")
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 加载数据源的配置信息
     * @return
     */
    @Bean("secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties("spring.datasource.secondary.configuration")
    public DataSource secondaryDataSource() {
        return secondaryDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)// 3. 可以显示指定连接池，也可以不显示指定；即此行代码可以注释掉
                .build();
    }

    /**
     * 加载template类
     * @return
     * @throws Exception
     */
    @Bean(value = "secondaryTransactionManager")
    @Qualifier("secondaryTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        System.out.println("=============================================================");
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 加载sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("secondarySqlSessionFactory")
    @Qualifier("secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
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
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}

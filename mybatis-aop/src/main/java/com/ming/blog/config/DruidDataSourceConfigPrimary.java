package com.ming.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ming.blog.anno.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
//@MapperScan(basePackages = {"com.ming.blog.dao.primary"},
//            sqlSessionTemplateRef = "primarySqlSessionTemplate")
public class DruidDataSourceConfigPrimary {

    @Autowired
    private Environment env;

    /**
     * 加载配置文件中的数据源基本数据
     * @return
     */
    @Bean("primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DruidDataSource primaryDataSourceProperties() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.addFilters("stat,wall,log4j");
        return druidDataSource;
    }

    /**
     * 加载PlatformTransactionManager类
     * @return
     * @throws Exception
     */
    @Bean("primaryTransactionManager")
    @Qualifier("primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

// =====================================================================================================

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
     * 加载PlatformTransactionManager类
     * @return
     * @throws Exception
     */
    @Bean("secondaryTransactionManager")
    @Qualifier("secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    // ===============================================================================================================
    /**
     * 动态数据库配置
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSourceProperties());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("primaryDataSource", primaryDataSourceProperties());
        dsMap.put("secondaryDataSource", secondaryDataSourceProperties());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                               @Qualifier("secondaryDataSource") DataSource secondaryDataSource) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dynamicDataSource());
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return fb.getObject();
    }

//    /**
//     * 配置事务管理器
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager() throws Exception {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }

}

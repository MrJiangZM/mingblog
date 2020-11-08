package com.ming.blog.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.ming.blog.dao.secondary"},
//            sqlSessionTemplateRef = "secondarySqlSessionTemplate")
            sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class DruidDataSourceConfigSecondary {

    @Autowired
    private Environment env;

    @Bean("secondaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.secondary")
    public DruidXADataSource secondaryDataSource() {
        return new DruidXADataSource();
    }


    @Bean("secondaryDataSourceJTA")
    public DataSource primaryDataSourceProperties(@Qualifier("secondaryDataSource") DataSource dataSource) {

//        druidXADataSource.setConnectionProperties("spring.datasource.druid.secondary");

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource((DruidXADataSource)dataSource);
        atomikosDataSourceBean.setUniqueResourceName("secondaryDataSourceJTA");
        atomikosDataSourceBean.setPoolSize(20);
        // 这里可以设置连接池的基本配置

        return atomikosDataSourceBean;
    }

    /**
     * 加载sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean("secondarySqlSessionFactory")
    @Qualifier("secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSourceJTA") DataSource dataSource) throws Exception {
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

    private Properties build(String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName"));
        return prop;
    }

}

package com.ming.blog.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(basePackages = {"com.ming.blog.dao.primary"},    // 最后设置成不同分包的设置
            sqlSessionTemplateRef = "primarySqlSessionTemplate")
//            sqlSessionFactoryRef = "primarySqlSessionFactory")
public class DruidDataSourceConfigPrimary {

    @Autowired
    private Environment env;

    @Bean(value = "primaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DruidXADataSource primaryDataSource () {
        return new DruidXADataSource();
    }

    @Bean("primaryDataSourceJTA")
    @Primary
    public DataSource primaryDataSourceJTA(@Qualifier("primaryDataSource") DataSource dataSource) {
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource((DruidXADataSource)dataSource);
        atomikosDataSourceBean.setUniqueResourceName("primaryDataSourceJTA");
        atomikosDataSourceBean.setPoolSize(20);
        return atomikosDataSourceBean;
    }

    /**
     * 会出现jta连接池创建失败的问题，使用普通数据源没问题
     *
     *
     * 加载sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("primarySqlSessionFactory")
    @Qualifier("primarySqlSessionFactory")
//    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSourceJTA") DataSource dataSource) throws Exception {
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


    /*
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     * */
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
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

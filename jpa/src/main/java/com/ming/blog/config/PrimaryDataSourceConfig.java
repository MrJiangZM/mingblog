package com.ming.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.ming.blog.*.dao.primary"})
public class PrimaryDataSourceConfig {

    @Autowired
    private HibernateProperties hibernateProperties;
    @Resource(name = "primaryDataSource")
    private DataSource primaryDataSource;
    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "primaryEntityManager")
    public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder) {
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }

    /**
     * 设置实体类所在位置
     */
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(primaryDataSource)
                .packages("com.ming.blog.*.pojo.primary") //com.ming.blog.money.dao.primary.StudentMoneyDao
                .persistenceUnit("primaryPersistenceUnit")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }



// =====================旧版springboot配置方法====================================================

//    @Primary
//    @Bean(name = "primaryEntityManagerFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(primaryDataSource)
//                .properties(getVendorProperties(primaryDataSource))
//                .packages("com.ming.blog.**.dao.primary") //设置实体类所在位置
//                //持久化单元名称，当存在多个EntityManagerFactory时，需要制定此名称
//                .persistenceUnit("primaryPersistenceUnit")
//                .build();
//    }

//    @Primary
//    @Bean(name = "primaryEntityManager")
//    public EntityManager entityManagerPrimary(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
//    }
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        HibernateSettings hibernateSettings = new HibernateSettings(); // 这个是干嘛的？？？
//        jpaProperties.getHibernateProperties(dataSource); // 最新的springbootjpa没有该方法
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
//    }

}
